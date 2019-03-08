package springboot_demo1.demo.controller;


import com.mathworks.toolbox.javabuilder.external.org.json.JSONException;
import com.mathworks.toolbox.javabuilder.external.org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springboot_demo1.demo.service.toDb_Service;

import java.util.List;


@RestController
public class toDb_Controller {
    //用于地图点击 右边显示选项参数的获取
    toDb_Service ts = new toDb_Service();

    @RequestMapping(value = "/input_data", method = RequestMethod.POST)

    public List to_db(@RequestBody String s) throws Exception {
        System.out.println("!!!");
//        String s = ss+"";
        System.out.println(s);
        toDb_Service ts = new toDb_Service();
        List list = ts.to_db(s);

        return list;
//        return null;

    }

    //用于获取用户填写的数据信息，存入数据库
    @RequestMapping(value = "/dataToDb", method = RequestMethod.POST)

    public List dataToDb(@RequestBody String l) throws Exception {
        if (l != null) {
            System.out.println(l);
        }
        ts.dataToDb(l);

        return null;


    }
    //获取查询数据的下拉选项 返回数据同1


    @RequestMapping(value = "/getParam", method = RequestMethod.POST)

    public List selectParams(@RequestBody String s) throws Exception {
//        System.out.println(s);
        toDb_Service ts = new toDb_Service();
        List list = ts.getParam(s);

        return list;


    }
}
