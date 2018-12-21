package springboot_demo1.demo.controller;

import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import springboot_demo1.demo.domain.uploadFile;
import springboot_demo1.demo.service.uploadFileService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UploadFileController {


    //多文件上传testUploadFiles
    @RequestMapping(value = "/testUploadFiles", method = RequestMethod.POST)
    @ResponseBody
    //HttpServletRequest request, HttpServletResponse response
    public uploadFile handleFileUpload(@RequestParam("file") MultipartFile file, HttpSession httpSession) throws Exception {
        Map map = new HashMap();
        uploadFile uf = new uploadFile();
        if (!file.isEmpty()) {


            uf.uploadFileName = file.getOriginalFilename();

            System.out.println("已经获取到文件：" + uf.uploadFileName);
            //上传到的文件全路径为：
            uf.uploadFilePath = "//Users//dongbudong//IdeaProjects//SpringBoot_demo1//src//main//resources//static//xlsx//" + uf.uploadFileName;
            System.out.println("文件路径为 ：" + uf.uploadFilePath);
            uf.uploadFilePath = uf.uploadFilePath;
            uf.uploadFileSize = file.getSize();
            BufferedOutputStream stream = null;
            stream = new BufferedOutputStream(new FileOutputStream(new File(uf.uploadFilePath)));
            byte[] bytes = file.getBytes();
            stream.write(bytes, 0, bytes.length);
            uploadFileService ufs = new uploadFileService();
            List list = ufs.uploadService(uf);

        }


        return uf;
    }
//        List list = new ArrayList();
//
////        List<MultipartFile> files = ((MultipartHttpServletRequest) request)
////                .getFiles("file");
//        MultipartFile file = null;
//        BufferedOutputStream stream = null;
////        for (int i = 0; i < files.size(); ++i) {
////            file = files.get(i);
//            if (!file.isEmpty()) {
//                try {
//                    String uploadFileName = file.getOriginalFilename();
//                    System.out.println("文件名 :" + uploadFileName);
//
//                    String uploadFilePath = "//Users//dongbudong//IdeaProjects//SpringBoot_demo1//src//main//resources//static//xlsx//"+uploadFileName;
//                    System.out.println("上传到的文件路径为："+uploadFilePath);
//                    stream = new BufferedOutputStream(new FileOutputStream(new File(uploadFilePath)));
//                    byte[] bytes = file.getBytes();
//                    stream.write(bytes, 0, bytes.length);
////                    response.setContentType("text/html;charset=gb2312");
////                    response.getWriter().write("上传成功！！");
////                    list.add(uploadFileName);
//////                    HttpSession session = request.getSession();
////                    request.setAttribute("file_list",list);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    System.out.println("上传失败");
//                } finally {
//                    try {
//                        if (stream != null) {
//                            stream.close();
//                        }
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            } else {
//                System.out.println("上传文件为空");
//            }
//        }
//
//
////        Thread.sleep(10000);
////        response.sendRedirect("http://localhost:8080/upload.html");


//    //文件下载
//    @RequestMapping(value = "/testDownload", method = RequestMethod.GET)
//    public void testDownload(HttpServletResponse res) {
//        String fileName = "upload.jpg";
//        res.setHeader("content-type", "application/octet-stream");
//        res.setContentType("application/octet-stream");
//        res.setHeader("Content-Disposition", "attachment;filename=" + fileName);
//        byte[] buff = new byte[1024];
//        BufferedInputStream bis = null;
//        OutputStream os = null;
//        try {
//            os = res.getOutputStream();
//            bis = new BufferedInputStream(new FileInputStream(new File("d://"
//                    + fileName)));
//            int i = bis.read(buff);
//            while (i != -1) {
//                os.write(buff, 0, buff.length);
//                os.flush();
//                i = bis.read(buff);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (bis != null) {
//                try {
//                    bis.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        System.out.println("success");
//    }

    //单文件上传
//    @RequestMapping(value = "/testUploadFile", method = RequestMethod.POST)
//    public void testUploadFile(HttpServletRequest req,
//                               MultipartHttpServletRequest multiReq) {
//        // 获取上传文件的路径
//        String uploadFilePath = multiReq.getFile("file1").getOriginalFilename();
//        System.out.println("uploadFilePath:  " + uploadFilePath);
//        // 截取上传文件的文件名
//        String uploadFileName = uploadFilePath.substring(
//                uploadFilePath.lastIndexOf('\\') + 1, uploadFilePath.indexOf('.'));
//        System.out.println("multiReq.getFile():    " + uploadFileName);
//        // 截取上传文件的后缀
//        String uploadFileSuffix = uploadFilePath.substring(
//                uploadFilePath.indexOf('.') + 1, uploadFilePath.length());
//        System.out.println("uploadFileSuffix:    " + uploadFileSuffix);
//        FileOutputStream fos = null;
//        FileInputStream fis = null;
//        try {
//            fis = (FileInputStream) multiReq.getFile("file1").getInputStream();
//            fos = new FileOutputStream(new File(".//uploadFiles//" + uploadFileName
//                    + ".")
//                    + uploadFileSuffix);
//            byte[] temp = new byte[1024];
//            int i = fis.read(temp);
//            while (i != -1) {
//                fos.write(temp, 0, temp.length);
//                fos.flush();
//                i = fis.read(temp);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (fis != null) {
//                try {
//                    fis.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (fos != null) {
//                try {
//                    fos.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
}

