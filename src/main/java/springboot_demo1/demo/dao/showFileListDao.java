package springboot_demo1.demo.dao;

import springboot_demo1.demo.tools.linkMysql;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class showFileListDao {


    public List selectFileList() {

        List fileList = new ArrayList();
        linkMysql linkmysql = new linkMysql();
        try {
            Statement stat = linkmysql.connectMysql();
            String sql = "select uploadFileName from uploadFile";
            ResultSet result = stat.executeQuery(sql);

            while(result.next()){
               fileList.add(result.getString("uploadFileName")) ;
            }
           // System.out.println(fileList.size());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileList;
    }
}
