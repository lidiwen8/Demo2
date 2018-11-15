package service;

import dao.UserDao;
import entity.Userprize;
import entity.User;
import util.PageBean;

import java.sql.SQLException;

public class UserService {
    UserDao userDao = new UserDao();

    public int add(User user) throws Exception {
        return userDao.register(user);
    }

    public User queryUser(String name) {
        return userDao.queryUser(name);
    }

    public Userprize queryUserprize(String name) {
        return userDao.queryUserprize(name);
    }

    public Userprize queryUserprize() {
        return userDao.queryUserprize();
    }
//    public PageBean<Student> findStudent(int pc, int pr, String studentname){
//        return userDao.findStudent(pc,pr,studentname);
//    }//分页查找学生信息


}
