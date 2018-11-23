package springboot_demo1.demo.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import springboot_demo1.demo.domain.User;
import springboot_demo1.demo.domain.excel_data;
import springboot_demo1.demo.tools.ExcelToHTML;

import java.io.File;


@RestController
public class uploadController {
    //给方法获取
    @RequestMapping(value = "/upload/getPath", method = RequestMethod.POST)
    public String getPath(@RequestBody excel_data e) {
        System.out.println("controller!!");


        String source_path = e.getFile_path();

        ExcelToHTML eth = new ExcelToHTML();

        String html_path = eth.readExcelToHtml(source_path,
                "/Users/dongbudong/IdeaProjects/SpringBoot_demo1/src/main/resources/static/excel.html",
                false);
        System.out.println(html_path);
        File file = new File("/Users/dongbudong/IdeaProjects/SpringBoot_demo1/src/main/resources/static/excel.html");


        return html_path;
    }


////    @RequestMapping(value = "upload/getPath", method = RequestMethod.POST)
//    public String getPath(@RequestBody MultipartFile file) {
//        System.out.println("controller!!");
//        if (file == null) {
//            System.out.println("kong");
//        }else{
//            System.out.println("bukong");
//            System.out.println(file.getOriginalFilename());
//            System.out.println(file.getName());
//            System.out.println(file.getResource());
//        }
//
//
//        return "";
//    }
//

}