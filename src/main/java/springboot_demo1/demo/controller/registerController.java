package springboot_demo1.demo.controller;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springboot_demo1.demo.domain.User;
import springboot_demo1.demo.domain.User_data;
import springboot_demo1.demo.service.loginService;
import springboot_demo1.demo.service.registerService;

import java.util.List;

@RestController
public class registerController {

    registerService rs = new registerService();

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String login(@RequestBody User_data u) throws Exception {


        rs.registerService(u);
        System.out.println(u.getBirth());

        return "注册成功";
    }

    @RequestMapping(value = "/checkUsername", method = RequestMethod.POST)
    public String checkUsername(@RequestBody String s) throws Exception {
        String username = s.split(":")[1].replace("}", "");
        String result = rs.checkUsername(username);
        System.out.println(username);
        return result;
    }
}
