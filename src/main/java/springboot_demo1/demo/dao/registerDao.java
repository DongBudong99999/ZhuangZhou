package springboot_demo1.demo.dao;

import springboot_demo1.demo.domain.User_data;
import springboot_demo1.demo.tools.linkMysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class registerDao {
    linkMysql linkmysql = new linkMysql();
    Statement stat = null;

    public void registerDao(User_data u) {
        String name = u.getName();
        String password = u.getPassword();
        String gender = u.getGender();
        String age = u.getAge();
        String birth = u.getBirth();
        String phone = u.getPhone();
        String email = u.getEmail();
        System.out.println(name + " " + password + "  " + gender + " " + age + " " + birth + " " + phone + " " + email);

        try {
            stat = linkmysql.connectMysql();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String sql = "insert into  user(name,password,age,birth,gender,phone,email) values ('" + name + "','" + password + "','" + age + "','" + birth + "','" + gender + "','" + phone + "','" + email + "')";
        System.out.println(sql);
        try {
            stat.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public String checkUsername(String s) throws Exception {
        String result = "";
        String sql = "select * from user where name=" + s + ";";
        System.out.println(sql);
        stat = linkmysql.connectMysql();
        ResultSet rs = stat.executeQuery(sql);


        if (rs.next()) {
            result = "该用户名以存在，请更换";
        } else {
            result = "该用户名通过检测";


        }
        return result;
    }
}
