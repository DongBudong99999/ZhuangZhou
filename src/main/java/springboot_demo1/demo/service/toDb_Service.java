package springboot_demo1.demo.service;

import springboot_demo1.demo.dao.toDb_Dao;

import java.util.List;

public class toDb_Service {

    toDb_Dao td = new toDb_Dao();
    public List to_db(String s) throws Exception {

        List list = td.todb(s);
        return list;
    }

    public void dataToDb(String l) throws Exception {
        td.dataToDb(l);
    }

    public List getParam(String s) throws Exception {
        List list = td.getParam(s);
        return list;
    }
}
