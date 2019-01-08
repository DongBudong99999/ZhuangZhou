package springboot_demo1.demo.service;

import springboot_demo1.demo.dao.selectInfoDao;
import springboot_demo1.demo.domain.selectInfo;

import java.util.ArrayList;
import java.util.List;


public class selectInfoService {
    public List getInfo(String s) throws Exception {
        selectInfoDao sid = new selectInfoDao();
        List info_list = sid.getInfo(s);
        return info_list;
    }

    public List getTitle() throws Exception {
        selectInfoDao sid = new selectInfoDao();
        List titleList = sid.getTitle();
        return titleList;
    }
}
