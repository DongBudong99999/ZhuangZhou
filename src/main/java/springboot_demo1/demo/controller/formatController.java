package springboot_demo1.demo.controller;


import com.mathworks.toolbox.javabuilder.external.org.json.JSONException;
import com.mathworks.toolbox.javabuilder.external.org.json.JSONObject;
import org.apache.commons.codec.binary.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
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
            System.out.println(G调);
            ;
            double result_double = K3 * K4 * J水 * K1 * K2 * G调;
            result = String.valueOf(result_double);
            System.out.println(result);

        }

        if (json.getString("id").contains("净化水质")) {
            double K3 = Double.parseDouble(json.getString("K3"));
            System.out.println(K3);
            double K4 = Double.parseDouble(json.getString("K4"));
            System.out.println(K4);
            double J污水 = Double.parseDouble(json.getString("J污水"));
            System.out.println(J污水);
            double K1 = Double.parseDouble(json.getString("K1"));
            System.out.println(K1);
            double K2 = Double.parseDouble(json.getString("K2"));
            System.out.println(K1);
            double G调 = Double.parseDouble(json.getString("G调"));
            System.out.println(G调);


            double result_double = K3 * K4 * J污水 * K1 * K2 * G调;
            result = String.valueOf(result_double);

            System.out.println(result);

        }
        if (json.getString("id").contains("防止土壤侵蚀")) {
            double K3 = Double.parseDouble(json.getString("K3"));
            System.out.println(K3);
            double K4 = Double.parseDouble(json.getString("K4"));
            System.out.println(K4);
            double K1 = Double.parseDouble(json.getString("K1"));
            System.out.println(K1);
            double K2 = Double.parseDouble(json.getString("K2"));
            System.out.println(K1);
            double G固土 = Double.parseDouble(json.getString("G固土"));
            System.out.println(G固土);
            double J土 = Double.parseDouble(json.getString("J土"));
            System.out.println(J土);
            double ρ = Double.parseDouble(json.getString("ρ"));
            System.out.println(ρ);

            double result_double = (K3 * K4 * K1 * K2 * G固土 * J土 / ρ);
            result = String.valueOf(result_double);

            System.out.println(result);

        }
        if (json.getString("id").contains("植被固碳")) {
            double K1 = Double.parseDouble(json.getString("K1"));
            System.out.println(K1);
            double K3 = Double.parseDouble(json.getString("K3"));
            System.out.println(K3);


            double G植被固碳 = Double.parseDouble(json.getString("G植被固碳"));
            System.out.println(G植被固碳);
            double J碳 = Double.parseDouble(json.getString("J碳"));
            System.out.println(J碳);
            double result_double = (K3 * K1 * G植被固碳 * J碳);
            result = String.valueOf(result_double);
            System.out.println(result);

        }

        if (json.getString("id").contains("释放氧气")) {
            double K1 = Double.parseDouble(json.getString("K1"));
            System.out.println(K1);
            double K3 = Double.parseDouble(json.getString("K3"));
            System.out.println(K3);
            double G氧气 = Double.parseDouble(json.getString("G氧气"));
            System.out.println(G氧气);
            double J氧 = Double.parseDouble(json.getString("J氧"));
            System.out.println(J氧);
            double result_double = K3 * K1 * G氧气 * J氧 / 20.0;
            result = String.valueOf(result_double);
            System.out.println(result);

        }


        if (json.getString("id").contains("吸收SO2")) {
            double K1 = Double.parseDouble(json.getString("K1"));
            System.out.println(K1);
            double K3 = Double.parseDouble(json.getString("K3"));
            System.out.println(K3);
            double G二氧化硫 = Double.parseDouble(json.getString("G二氧化硫"));
            System.out.println(G二氧化硫);
            double J二氧化硫 = Double.parseDouble(json.getString("J二氧化硫"));
            System.out.println(J二氧化硫);
            double result_double = K3 * K1 * G二氧化硫 * J二氧化硫;
            result = String.valueOf(result_double);
            System.out.println(result);

        }


        if (json.getString("id").contains("吸收氟化物")) {
            double K1 = Double.parseDouble(json.getString("K1"));
            System.out.println(K1);
            double K3 = Double.parseDouble(json.getString("K3"));
            System.out.println(K3);
            double G氟化物 = Double.parseDouble(json.getString("G氟化物"));
            System.out.println(G氟化物);
            double J氟化物 = Double.parseDouble(json.getString("J氟化物"));
            System.out.println(J氟化物);
            double result_double = K3 * K1 * G氟化物 * J氟化物;
            result = String.valueOf(result_double);
            System.out.println(result);

        }


        if (json.getString("id").contains("吸收氮氧化物")) {
            double K1 = Double.parseDouble(json.getString("K1"));
            System.out.println(K1);
            double K3 = Double.parseDouble(json.getString("K3"));
            System.out.println(K3);
            double G氮氧化物 = Double.parseDouble(json.getString("G氮氧化物"));
            System.out.println(G氮氧化物);
            double J氮氧化物 = Double.parseDouble(json.getString("J氮氧化物"));
            System.out.println(J氮氧化物);
            double result_double = K3 * K1 * G氮氧化物 * J氮氧化物;
            result = String.valueOf(result_double);
            System.out.println(result);

        }

        if (json.getString("id").contains("吸收重金属")) {
            double K1 = Double.parseDouble(json.getString("K1"));
            System.out.println(K1);
            double K3 = Double.parseDouble(json.getString("K3"));
            System.out.println(K3);
            double G重金属 = Double.parseDouble(json.getString("G重金属"));
            System.out.println(G重金属);
            double J重金属 = Double.parseDouble(json.getString("J重金属"));
            System.out.println(J重金属);
            double result_double = K3 * K1 * G重金属 * J重金属;
            result = String.valueOf(result_double);
            System.out.println(result);

        }


        if (json.getString("id").contains("滞尘")) {
            double K1 = Double.parseDouble(json.getString("K1"));
            System.out.println(K1);
            double K3 = Double.parseDouble(json.getString("K3"));
            System.out.println(K3);
            double G滞尘 = Double.parseDouble(json.getString("G滞尘"));
            System.out.println(G滞尘);
            double J滞尘 = Double.parseDouble(json.getString("J滞尘"));
            System.out.println(J滞尘);
            double result_double = K3 * K1 * G滞尘 * J滞尘;
            result = String.valueOf(result_double);
            System.out.println(result);

        }


        if (json.getString("id").contains("释放负离子")) {
            double K1 = Double.parseDouble(json.getString("K1"));
            System.out.println(K1);
            double K3 = Double.parseDouble(json.getString("K3"));
            System.out.println(K3);
            double G负离子 = Double.parseDouble(json.getString("G负离子"));
            System.out.println(G负离子);
            double J负离子 = Double.parseDouble(json.getString("J负离子"));
            System.out.println(J负离子);
            double result_double = K3 * K1 * G负离子 * J负离子;
            result = String.valueOf(result_double);
            System.out.println(result);

        }


        if (json.getString("id").contains("牧场防护")) {
            double K4 = Double.parseDouble(json.getString("K4"));
            System.out.println(K4);
            double K3 = Double.parseDouble(json.getString("K3"));
            System.out.println(K3);
            double G牧草量 = Double.parseDouble(json.getString("G牧草量"));
            System.out.println(G牧草量);
            double J牧草 = Double.parseDouble(json.getString("J牧草"));
            System.out.println(J牧草);
            double result_double = K3 * K4 * G牧草量 * J牧草;
            result = String.valueOf(result_double);
            System.out.println(result);

        }

        if (json.getString("id").contains("农田防护")) {
            double K4 = Double.parseDouble(json.getString("K4"));
            System.out.println(K4);
            double K3 = Double.parseDouble(json.getString("K3"));
            System.out.println(K3);
            double G农作物量 = Double.parseDouble(json.getString("G农作物量"));
            System.out.println(G农作物量);
            double J农作物 = Double.parseDouble(json.getString("J农作物"));
            System.out.println(J农作物);
            double result_double = K3 * K4 * G农作物量 * J农作物;
            result = String.valueOf(result_double);
            System.out.println(result);

        }

        if (json.getString("id").contains("物种保育")) {
            double K2 = Double.parseDouble(json.getString("K2"));
            System.out.println(K2);
            double K3 = Double.parseDouble(json.getString("K3"));
            System.out.println(K3);
            double K4 = Double.parseDouble(json.getString("K4"));
            System.out.println(K4);

            double Em = Double.parseDouble(json.getString("Em"));
            System.out.println(Em);
            double Bn = Double.parseDouble(json.getString("Bn"));
            System.out.println(Bn);
            double Oi = Double.parseDouble(json.getString("Oi"));
            System.out.println(Oi);

            double S物种保育 = Double.parseDouble(json.getString("S物种保育"));
            System.out.println(S物种保育);
            double S = Double.parseDouble(json.getString("S"));
            System.out.println(S);

            double t = Double.parseDouble(json.getString("t"));
            System.out.println(t);
            DecimalFormat    df   = new DecimalFormat("######0.00");

            double result_double = K2 * K3 * K4 * (1 + 0.1 * Em + 0.1 * Bn + 0.1 * Oi) * S物种保育 * S * t;
            result = df.format(result_double);


            System.out.println(result);

        }


        return result;


    }
}
