package springboot_demo1.demo.controller;


import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(Map<String, Object> map) {
        map.put("name", "HelloController");
        return "index";
    }
}