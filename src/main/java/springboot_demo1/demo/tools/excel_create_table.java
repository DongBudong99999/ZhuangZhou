package springboot_demo1.demo.tools;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;


import java.io.BufferedWriter;
import java.io.File;
import java.sql.ResultSet;
import java.sql.Statement;

public class excel_create_table {

    public static void main(String[] args) throws Exception {

        String source = "//Users//dongbudong//Desktop//Tables//test1.xls";

        //创建表test
        linkMysql link = new linkMysql();
        Statement stat = link.connectMysql();
        ResultSet resultSet = stat.executeQuery("select * from information_schema.TABLES where table_schema ='xiaoban datas' and table_name = 'test1';");

        if (resultSet.next()){
            System.out.println("该表已经存在，准备删除 - -");
            stat.execute("drop table test1");
        }
        stat.executeUpdate("create table test1(id int ,CONSTRAINT pk_id PRIMARY KEY(id))");
        System.out.println("表创建完毕 - -");
        addcolumn(source, stat);
        System.out.println("列追加完毕");
    }

    public static void addcolumn(String source, Statement stat) throws Exception {

        int indexColumn = 0;
        BufferedWriter output = null;
        File file = new File(source);
        jxl.Workbook wb = jxl.Workbook.getWorkbook(file);

        jxl.Sheet[] sheets = wb.getSheets();
        // 遍历每页
        for (jxl.Sheet s : sheets) {
            System.out.println("--------------------------------");
            System.out.println("本页页名：" + s.getName() + " : ");
            //output.write(s.getName() + " : ");
            int rows = s.getRows();
            System.out.println("rows:"+ rows);
            System.out.println("总行数：" + rows);
            if (rows > 0) {
                // 遍历每行
                for (int i = 0; i < 1; i++) {
                    jxl.Cell[] cells = s.getRow(i);
                    if (cells.length > 0) {
                        // 遍历每行中的每列
                        for (int j=1;j<cells.length;j++) {

                            String str = cells[j].getContents().trim();
                            stat.execute("alter table test1 add " + str + " varchar(30)");
//                            System.out.printf("%10s", str);
                            System.out.printf( str+",");

                        }
                    }
                    System.out.println("");
                }

            }

        }
    }


}
