package springboot_demo1.demo.service;

import springboot_demo1.demo.dao.loginDao;
import springboot_demo1.demo.domain.User;

import java.util.List;

public class loginService {
    public List loginService(String username, String password) throws Exception {
        loginDao logindao = new loginDao();
        List list = logindao.loginDao(username, password);


        return list;


    }
}
