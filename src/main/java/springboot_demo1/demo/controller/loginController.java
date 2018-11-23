package springboot_demo1.demo.controller;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springboot_demo1.demo.domain.User;
import springboot_demo1.demo.service.loginService;

import java.util.List;

@RestController
public class loginController {

    @RequestMapping(value = "/loginForm", method = RequestMethod.POST)
    public List login(@RequestBody User u) throws Exception {
        System.out.println("进入loginController！！");
        String username = u.getUsername();
        String password = u.getPassword();
        System.out.println(username + password);
        loginService loginService= new loginService();
        List list = loginService.loginService(username, password);
        if(list==null){
            return  null;
        }else{

            return  list;
        }



    }


}
