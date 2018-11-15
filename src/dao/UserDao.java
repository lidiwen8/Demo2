package dao;

import cn.itcast.jdbc.TxQueryRunner;
import entity.User;
import entity.Userprize;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import util.PageBean;
import util.RowMapperList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private QueryRunner mysqlDao = new TxQueryRunner();

    public User login(String name, String password) {
        try {
//            if ((mysqlDao.query("select * from login where passerword=?", new BeanHandler<JPo>(JPo.class), passerword) != null) && (mysqlDao.query("select * from login where name=?", new BeanHandler<JPo>(JPo.class), name) != null)){
            return mysqlDao.query("select * from login where name=? and password=?", new BeanHandler<User>(User.class), name, password);
//            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    // 查询所有记录
    public static List<Userprize> query(String sql) {
        Object[] values = new Object[]{};
        UserprizeRML rowMapperList = new UserprizeRML();
        List<Userprize> list = GeneralDao.query(sql, values, rowMapperList);
        return list;
    }

    public User queryUser(String name) {
        try {
            return mysqlDao.query("select * from login where name=?", new BeanHandler<User>(User.class), name);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public Userprize queryUserprize(String name) {
        try {
            return mysqlDao.query("select *from userprize where name=?", new BeanHandler<Userprize>(Userprize.class), name);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public Userprize queryUserprize() {
        try {
            return mysqlDao.query("select *from userprize", new BeanHandler<Userprize>(Userprize.class));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public int register(User user) throws Exception {
        int flag = 0;
        String sql = "insert into login(name,password,sex) values(?,?,?)";
        Object[] params = {user.getName(), user.getPassword(), user.getSex()};
        try {
            //事务开始

            mysqlDao.update(sql, params);
            flag = 1;
            //事务提交
        } catch (Exception e) {
            e.printStackTrace();
            //事务回滚
            flag = 0;
            throw e;
        }
        return flag;
    }

    static class UserprizeRML implements RowMapperList {

        public List<Userprize> rowMapping(ResultSet rs) throws SQLException {
            List<Userprize> list = new ArrayList<Userprize>();
            // 如果rs有数据
            if (rs.next()) {
                do {
                    Userprize userprize = new Userprize();
                    userprize.setId(rs.getInt("id"));
                    userprize.setName(rs.getString("name"));
                    userprize.setIP(rs.getString("ip"));
                    userprize.setPrize(rs.getString("prize"));
                    userprize.setDate(rs.getString("date"));
                    userprize.setComent(rs.getString("coment"));
                    userprize.setNumber(rs.getString("number"));
                    userprize.setState(rs.getInt("state"));
                    list.add(userprize);
                } while (rs.next());
            } else {
                return null;
            }
            return list;
        }
    }

//    public PageBean<Userprize> findAllprize(int pc, int pr)
//    {
//        try{
//            /*
//             *1.他需要创建pageBean对象pb
//             * 2.设置pb的pc和pr
//             * 3.得到tr，设置给pb
//             * 4.得到beanList设置给pb
//             * 最后返回给pb
//             */
//            PageBean< Userprize> pb=new PageBean<Userprize>();
//            pb.setPc(pc);
//            pb.setPr(pr);
//
//            String sql="select count(*) from  userprize";
//            Number number=(Number) mysqlDao.query(sql,new ScalarHandler<>());
//
//            int tr=number.intValue();
//            pb.setTr(tr);
//
//            sql="select * from  userprize order by name limit ?,?";
//            Object[] params={(pc-1)*pr,pr};
//            List< Userprize> beanList=mysqlDao.query(sql,new BeanListHandler<Userprize>( Userprize.class),params);
//
//            pb.setBeanList(beanList);
//
//            return pb;
//        }catch (Exception e)
//        {
//            throw new RuntimeException(e);
//        }
//    }


}
