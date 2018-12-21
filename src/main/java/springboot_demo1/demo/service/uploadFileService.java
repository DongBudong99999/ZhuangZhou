package springboot_demo1.demo.service;

import springboot_demo1.demo.dao.uploadFileDao;
import springboot_demo1.demo.domain.uploadFile;

import java.util.List;

public class uploadFileService {
    public List uploadService(uploadFile uf) throws Exception {
        uploadFileDao ud = new uploadFileDao();
        List list = ud.uploadFileDao(uf);

        return  null;
    }
}
