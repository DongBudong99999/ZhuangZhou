package springboot_demo1.demo.controller;


import com.mathworks.toolbox.javabuilder.external.org.json.JSONException;
import com.mathworks.toolbox.javabuilder.external.org.json.JSONObject;
import org.apache.commons.codec.binary.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class formatController {


    @RequestMapping(value = "/format", method = RequestMethod.POST)

    public String calcalute(@RequestBody String s) throws JSONException {
        String id = "";
        String result = "";
        String[] aa = s.replace("{", "").replace("}", "").split(",");
        JSONObject json = new JSONObject();
        json.put("1", "1");

        for (int i = 0; i < aa.length; i++) {

            json.put(aa[i].split(":")[0].replaceAll("\"", ""), aa[i].split(":")[1].replaceAll("\"", ""));


        }
        System.out.println(json);

        if (json.getString("id").contains("调节水量")) {
            double K3 = Double.parseDouble(json.getString("K3"));
            System.out.println(K3);
            double K4 = Double.parseDouble(json.getString("K4"));
            System.out.println(K4);
            double J水 = Double.parseDouble(json.getString("J水"));
            System.out.println(J水);
            double K1 = Double.parseDouble(json.getString("K1"));
            System.out.println(K1);
            double K2 = Double.parseDouble(json.getString("K2"));
            System.out.println(
                    K2
            );
            double G调 = Double.parseDouble(json.getString("G调"));
            System.out.println(G调);;
            double result_double = K3 * K4 * J水 * K1 * K2 * G调;
            result = String.valueOf(result_double);
            System.out.println(result);

        }


        return result;


    }
}
