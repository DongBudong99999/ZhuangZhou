package springboot_demo1.demo.controller;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springboot_demo1.demo.service.selectInfoService;
import springboot_demo1.demo.service.toDb_Service;

import java.util.List;


@RestController
public class selectInfoController {
    //用于查询符合选项的数据库记录

    @RequestMapping(value = "/select_info", method = RequestMethod.POST)

    public List getInfo(@RequestBody String s) throws Exception {
        selectInfoService si = new selectInfoService();
        List info_list = si.getInfo(s);

        for(int i=0;i<info_list.size();i++){
            System.out.println(info_list.get(i));
        }
        return info_list;


    }
    @RequestMapping(value = "/getTitle", method = RequestMethod.POST)

    public List getTitle() throws Exception {
        System.out.println("gettitle");
        selectInfoService si = new selectInfoService();
       List titleList =  si.getTitle();

//
//        for(int i=0;i<titleList.size();i++){
//            System.out.println(titleList.get(i));
//                  }
        return titleList;


    }

}
