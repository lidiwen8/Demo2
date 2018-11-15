package servlet;

import dao.UserDao;
import entity.Userprize;
import service.UserService;
import util.ExcelFileGenerator;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExcelServlet extends HttpServlet {
    UserService userService = new UserService();

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            //初始化fieldName，fieldDate
            ArrayList fieldName = getFieldName();    //excel标题数据集
            ArrayList fieldData = getFieldData();    //excel数据内容
            String myexcel = "lidiwen";
            //回去输出流
            OutputStream out = response.getOutputStream();
            //重置输出流
            response.reset();
            //设置导出Excel报表的导出形式
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename=" + myexcel + ".xls");
            ExcelFileGenerator efg = new ExcelFileGenerator(fieldName, fieldData);
            efg.expordExcel(out);
            //设置输出形式
            System.setOut(new PrintStream(out));
            //刷新输出流
            out.flush();
            //关闭输出流
            if (out != null) {
                out.close();
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
    }

    //模拟提供excel中的标题数据集
    public ArrayList getFieldName() {
        String str[] = {"id", "客户姓名", "ip地址", "获奖情况", "抽奖时间", "获得奖品", "联系方式", "状态"};
        ArrayList list = new ArrayList();
        for (int i = 0; i < str.length; i++) {
            list.add(str[i]);
        }
        return list;
    }

    //模拟提供excel中的标题数据内容
    public ArrayList getFieldData() {
        ArrayList list1 = new ArrayList();
        String sql = "select id,name,ip,prize,date,coment,number,state from userprize";//写在这不规范，将就一下
        List<Userprize> list2 = UserDao.query(sql);
        Userprize userprize = userService.queryUserprize();
        String str[][] = {{String.valueOf(userprize.getId()), userprize.getName(), userprize.getIP(), userprize.getPrize(), userprize.getDate(), userprize.getComent(), userprize.getNumber(), String.valueOf(userprize.getState())}};
        if (list2.size() > 0) {
            for (Userprize bean : list2) {
                ArrayList list = new ArrayList();
                list.add(bean.getId());
                list.add(bean.getName());
                list.add(bean.getIP());
                list.add(bean.getPrize());
                list.add(bean.getDate());
                list.add(bean.getComent());
                list.add(bean.getNumber());
                list.add(bean.getState());
                list1.add(list);
            }
        }
        return list1;

    }


}
