package springboot_demo1.demo.dao;


import springboot_demo1.demo.domain.selectInfo;
import springboot_demo1.demo.tools.linkMysql;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class selectInfoDao {
    public List getInfo(String s) throws Exception {


        linkMysql linkmysql = new linkMysql();
        Statement stat = linkmysql.connectMysql();
        System.out.println(s);


        String nolimit = s.replace(",", "  and  ");
//        System.out.println(nolimit);
        String sql = "select *  from user_xb_data where " + nolimit;
        String[] list = s.split(",");
        for (int i = 0; i < list.length; i++) {

            if (list[i].contains("查询条数")) {
                String limit = list[i].split("=")[1];
                int index = s.indexOf("查");
                String haslimit = null;
//                System.out.println(i);
                if (i != list.length - 1) {
                    if (i != 0) {
                        haslimit = s.substring(0, index - 1);
                        haslimit += s.substring(index + list[i].length(), s.length());
                    } else {
                        haslimit =  s.substring(list[i].length()+1,s.length());
                    }
                    System.out.println(haslimit);

                } else {

                    haslimit = s.substring(0, s.length() - list[i].length() - 1);
                }
//                System.out.println("修改后 ：" + haslimit);
                haslimit = haslimit.replace(",", "  and  ");
                sql = "select *  from user_xb_data where " + haslimit + " limit 0," + limit.replace("\"", "");
            }
        }
//        String sql = "select *  from user_xb_data where 林班号='3' limit 0,10";
        System.out.println("sql = " + sql);
        ResultSet rs = stat.executeQuery(sql);


        List<selectInfo> info_list = new ArrayList<>();

        while (rs.next()) {
            selectInfo info = new selectInfo();
            info.setLidileixing(rs.getString("立地类型"));
            info.setCaozuoshijian(rs.getString("操作时间"));
            info.setDiweiji(rs.getString("地位级"));
            info.setLinbanhao(rs.getString("林班号"));
            info.setLinfenleixing(rs.getString("林分类型"));
            info.setLingji(rs.getString("龄级"));
            info.setMeigongqingzhushu(rs.getString("每公顷株数"));
            info.setMianji(rs.getString("面积"));
            info.setPingjunshugao(rs.getString("平均树高"));
            info.setPodu(rs.getString("坡度"));
            info.setPowei(rs.getString("坡位"));
            info.setPoxiang(rs.getString("坡向"));
            info.setXiaobanhao(rs.getString("小班号"));
            info.setXiongjing(rs.getString("胸径"));
            info.setYubidu(rs.getString("郁闭度"));
            info.setXiaobanxuji(rs.getString("小班蓄积"));
            info.setLingzu(rs.getString("龄组"));

//            info_list.add(info);
//            System.out.println(info.toString());
            info_list.add(info);

        }
//        System.out.println("----------");
//        for (int i = 0; i < info_list.size(); i++) {
//            System.out.println(info_list.get(i));
//
//        }


        return info_list;
    }

    public List getTitle() throws Exception {
        linkMysql linkmysql = new linkMysql();
        Statement stat = linkmysql.connectMysql();

        String sql = "SELECT GROUP_CONCAT(COLUMN_NAME SEPARATOR \",\") FROM information_schema.COLUMNS \n" +
                "WHERE TABLE_SCHEMA = 'xiaoban_data' AND TABLE_NAME = 'user_xb_data'";
        ResultSet rs = stat.executeQuery(sql);
        List titleList = new ArrayList();
//        System.out.println(columnsCount);
        while (rs.next()) {
            System.out.println(rs.getString(1));
            String titleString = rs.getString(1);
            String[] ss = titleString.split(",");
            for (int i = 0; i < ss.length; i++) {
                System.out.println(ss[i]);
                titleList.add(ss[i]);

            }

        }
//        for (int j = 0; j < titleList.size(); j++) {
//            System.out.println(titleList.get(j));
//        }
        return titleList;
    }
}
