package springboot_demo1.demo.tools;

import jxl.Sheet;
import jxl.Workbook;
import springboot_demo1.demo.domain.data_demo1;

import java.io.File;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class excel_to_mysql {
    linkMysql link2 = new linkMysql();
    Statement stat;

//    public void tableService() {
//
//
//        {
//            try {
//                stat = link2.connectMysql();
//                String sql = "select * from demo2";
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        }
//    }

    public boolean isExist(int id) throws Exception {

        stat = link2.connectMysql();
        String sql = "select * from table1 where id=" + id;
        System.out.println(sql);
        ResultSet rs = stat.executeQuery(sql);

        if (rs.next()) {
            return true;
        }
        return false;

    }

    public List<data_demo1> getAllByExcel(String file) {
        List<data_demo1> list = new ArrayList<data_demo1>();

        try {
            Workbook rwb = Workbook.getWorkbook(new File(file));
            Sheet rs = rwb.getSheet(0);//或者rwb.getSheet(0)
            int clos = rs.getColumns();//得到所有的列
            int rows = rs.getRows();//得到所有的行
            System.out.println("clos: " + clos + " rows: " + rows);



            for (int i = 1; i < rows; i++) {

                List list_row = new ArrayList();

                for (int j = 0; j < clos; j++) {

                    //第一个是列数，第二个是行数
                    Object value = rs.getCell(j++, i).getContents().trim();

                    if ((value == "") || (value == null)) {
                        value = 0;
                    }
                    System.out.printf("%30s", value);
                    System.out.println(value);
                    list_row.add(value);

                    System.out.println(list_row.get(0));
                }
                // System.out.println();
                list.add((data_demo1) list_row);
            }
//            int index;
//            for (index = 0; index < list_row.size(); index++) {
//                System.out.println(list_row.get(index));
//            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }


    //    Excel表中的数据导入到MySql数据库
    public void tomysql(String source) {
        //得到表格中所有的数据
        List<data_demo1> listExcel = getAllByExcel(source);
        for (int i = 0; i < 80; i++) {
            for (int j = 0; j < 80; j++) {
                // System.out.print(listExcel[i][j]+" ");
            }
            // System.out.println();
        }

                   /*//得到数据库表中所有的数据
19         List<StuEntity> listDb=StuService.getAllByDb();*/

//        System.out.println("");
//        for (data_demo1[] data_demo1 : listExcel) {
//            for (Object data : data_demo1) {
//                int id = data.getId();
//                try {
//                    if (!isExist(id)) {
//                        System.out.println("该条id：" + id + "  不存在");
//                        //this.不存在就添加
//                        String sql = "insert into demo2 (lb,xb,joion,调查年份，功能分区) values(?,?,?)";
//                        String[] str = new String[]{data.getLb() + "", data.getXb() + "", data.getJoion() + "", data.get调查年份(), data.get功能分区()};
//                        stat.execute(sql, str);
//                    } else {
//                        //存在就更新
//                        System.out.println("该条id：" + id + "  存在");
//                        String sql = "update demo2 set lb=?,xb=?,joion=?, 调查年份=？，功能分区=？where id=?";
//                        String[] str = new String[]{data.getLb() + "", data.getXb() + "", data.getJoion() + "", data.get调查年份(), data.get功能分区(), id + ""};
//                        stat.execute(sql, str);
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        System.out.println("完毕～～");
    }
}


