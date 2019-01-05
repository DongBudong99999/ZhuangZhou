package springboot_demo1.demo.dao;

import springboot_demo1.demo.domain.User_data;
import springboot_demo1.demo.tools.linkMysql;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class toDb_Dao {
    public List todb(String s) throws Exception {

        linkMysql linkmysql = new linkMysql();
        Statement stat = linkmysql.connectMysql();
        String sql = "SELECT GROUP_CONCAT(COLUMN_NAME SEPARATOR \",\") FROM information_schema.COLUMNS \n" +
                "WHERE TABLE_SCHEMA = 'xiaoban_data' AND TABLE_NAME = 'user_xb_data'";
        ResultSet resultSet = stat.executeQuery(sql);
        List list = new ArrayList();
        while (resultSet.next()) {
            String params = resultSet.getString(1);
            String[] ss = params.split(",");
//            System.out.println(ss.length);
            for (int i = 0; i < ss.length; i++) {
                list.add(ss[i]);
            }

        }
//        System.out.println("____");
//        for(int j=0;j<list.size();j++){
//            System.out.println(list.get(j));
//        }
        return list;
    }

    public void dataToDb(String l) throws Exception {
        linkMysql linkmysql = new linkMysql();
        Statement stat = linkmysql.connectMysql();
        String sql = "insert into user_xb_data values" + "(" + l + ")";
//        System.out.println("-----------");
//        System.out.println(sql);
        stat.execute(sql);
    }

    public List getParam(String s) throws Exception {
        linkMysql linkmysql = new linkMysql();
        Statement stat = linkmysql.connectMysql();

        String sql = "select distinct  " + s + " from user_xb_data ";
        ResultSet resultSet = stat.executeQuery(sql);
        List list = new ArrayList();
        while (resultSet.next()) {
            list.add( resultSet.getString(s));

        }

        return list;
    }
}