package springboot_demo1.demo.service;

import springboot_demo1.demo.dao.SelectDataToChartDao;
import springboot_demo1.demo.domain.selectData;

import java.util.List;

public class SelectDataToChartService {
    public List service(selectData selectdata) {
        SelectDataToChartDao dao = new SelectDataToChartDao();
        List list = null;

        try {
            list = dao.dao(selectdata);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
