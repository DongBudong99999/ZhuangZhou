package springboot_demo1.demo.controller;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springboot_demo1.demo.domain.User;
import springboot_demo1.demo.domain.selectData;
import springboot_demo1.demo.service.SelectDataToChartService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class SelectDataToChartController {

    @RequestMapping(value = "/selectDataToChart", method = RequestMethod.POST)
    public List controller(@RequestBody selectData selectdata) throws Exception {
        System.out.println("进来了！！");

        SelectDataToChartService service = new SelectDataToChartService();

        List list = service.service(selectdata);

        return list;


    }
}
