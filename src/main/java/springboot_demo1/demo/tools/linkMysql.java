package springboot_demo1.demo.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class linkMysql {
    public Statement connectMysql() throws Exception {

        Class.forName("com.mysql.jdbc.Driver");

        //一开始必须填一个已经存在的数据库
        String url = "jdbc:mysql://localhost:3306/xiaoban_data?useUnicode=true&characterEncoding=utf-8";
        Connection conn = DriverManager.getConnection(url, "root", "root");
        Statement stat = conn.createStatement();
        if(stat!=null){
            System.out.println("数据库链接成功！");
        }
//        isExist is = new isExist();
//        boolean isExist = is.validateTableNameExist("demo2", conn);
//        if (isExist == true) {
//            System.out.println("该数据表已经存在");
//            return null;
//
//        } else {
//            return stat;
//        }

//        //创建数据库hello
//        stat.executeUpdate("create database hello");
//
//        //打开创建的数据库
//        stat.close();
//        conn.close();
//        url = "jdbc:mysql://localhost:3306/hello?useUnicode=true&characterEncoding=utf-8";
//        conn = DriverManager.getConnection(url, "root", "root");
//        stat = conn.createStatement();
        return stat;

    }
}
