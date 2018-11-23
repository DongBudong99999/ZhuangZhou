package springboot_demo1.demo.dao;

import springboot_demo1.demo.domain.User;
import springboot_demo1.demo.domain.User_data;
import springboot_demo1.demo.tools.linkMysql;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class loginDao {
    public List loginDao(String username, String password) throws Exception {
        linkMysql linkmysql = new linkMysql();
        Statement stat = linkmysql.connectMysql();
        String sql = "select * from user where name='" + username + "'";
        System.out.println(sql);
        ResultSet resultSet = stat.executeQuery(sql);
        ArrayList<User_data> userlist = new ArrayList<User_data>();
        User_data user_data = new User_data();
        while (resultSet.next()) {


            String password_fromdata = resultSet.getString("password");
            if (password_fromdata.equals(password)) {
                System.out.println("验证成功");
                user_data.setUsername(username);
                user_data.setPassword(password_fromdata);
                user_data.setAge(resultSet.getInt("age"));
                user_data.setBirth(resultSet.getString("birth"));
                user_data.setSex(resultSet.getString("sex"));
                userlist.add(user_data);

                return userlist;
            }

        }
        return null;
    }

}
