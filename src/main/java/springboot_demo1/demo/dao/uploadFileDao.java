package springboot_demo1.demo.dao;

import springboot_demo1.demo.domain.uploadFile;
import springboot_demo1.demo.tools.linkMysql;

import java.sql.Statement;
import java.util.List;

public class uploadFileDao {
    public List uploadFileDao(uploadFile uf) throws Exception {
        linkMysql linkmysql = new linkMysql();
        Statement stat = linkmysql.connectMysql();
       String size =  uf.uploadFileSize+"";
        String insertSql = "INSERT INTO uploadFile (uploadFileName, uploadFilePath,uploadFileSize) VALUES ('"+uf.uploadFileName+"','"+ uf.uploadFilePath+"',"+size+")";
        System.out.println(insertSql);
        stat.execute(insertSql);
        return null;
    }
}
