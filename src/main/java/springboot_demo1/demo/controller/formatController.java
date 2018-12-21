package springboot_demo1.demo.controller;


import org.apache.commons.codec.binary.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class formatController {


    @RequestMapping(value = "/format", method = RequestMethod.POST)

    public String calcalute(@RequestBody String s) {
        String[] aa=  s.replace("{", "").replace("}","").split(",");
        System.out.println(aa);

        return "123";


    }
}
