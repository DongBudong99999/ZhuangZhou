package springboot_demo1.demo.service;

import springboot_demo1.demo.dao.registerDao;
import springboot_demo1.demo.domain.User_data;

import java.sql.SQLException;

public class registerService {
    registerDao rd = new registerDao();

    public void registerService(User_data u) {

        rd.registerDao(u);

    }

    public String checkUsername(String username) throws Exception {

        String result = rd.checkUsername(username);
        return result;
    }
}
