package springboot_demo1.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import springboot_demo1.demo.dao.showFileListDao;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class showFileListController {

    @RequestMapping(value = "/showFileList", method = RequestMethod.POST)
    public List testUploadFile() {
        showFileListDao sfld = new showFileListDao();
        List fileList = sfld.selectFileList();
        return fileList;

    }
}
