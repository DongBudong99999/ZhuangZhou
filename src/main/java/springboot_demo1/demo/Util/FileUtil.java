package springboot_demo1.demo.Util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public class FileUtil {
    public static void uploadFile(MultipartFile file, String filePath,
                                  String fileName) throws Exception {
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        file.transferTo(new File(filePath + fileName));
    }
}
