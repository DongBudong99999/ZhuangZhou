package springboot_demo1.demo.dao;

import springboot_demo1.demo.domain.selectData;
import springboot_demo1.demo.tools.linkMysql;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SelectDataToChartDao {
    public List dao(selectData selectdata) throws Exception {
        List list = new ArrayList();
        String parameter = selectdata.getParameter();
        String startDate = selectdata.getStartDate();
        String endDate = selectdata.getEndDate();

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date start = format.parse(startDate);
        Date end = format.parse(endDate);
//int num = Integer.valueOf(selectdata.select_num);
        System.out.println(
                selectdata.select_num
        );
        String sql = "select " + parameter + " from user_xb_data limit 0," + selectdata.select_num + ";";
        System.out.println(sql);

        linkMysql linkmysql = new linkMysql();
        Statement stat = null;
        try {
            stat = linkmysql.connectMysql();
        } catch (Exception e) {
            e.printStackTrace();

        }
        ResultSet resultSet = stat.executeQuery(sql);
        while (resultSet.next()) {
            System.out.println(resultSet.getString("每公顷株数"));
            String num_string = resultSet.getString("每公顷株数");
            Integer integer = Integer.valueOf(num_string);
            list.add(integer);
//            System.out.println("done");


        }
        return list;
    }
}
