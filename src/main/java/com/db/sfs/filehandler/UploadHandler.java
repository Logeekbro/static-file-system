package com.db.sfs.filehandler;

import com.db.sfs.common.GlobalVars;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class UploadHandler {

    public void uploadMultipartFiles(MultipartFile file, String path) throws Exception {
        String fileName;
        String filePath;
            fileName = file.getOriginalFilename();
            filePath = GlobalVars.BASE_DIR + path + fileName ;
            Path destFile = Paths.get(filePath);
            if(Files.exists(destFile)){
                throw new Exception("文件已存在");
            }
            Files.copy(file.getInputStream(), destFile);
    }
    public boolean uploadMultipartFiles(MultipartFile[] files) {
        String fileName;
        String filePath;
        try{
            for(MultipartFile file : files){
                fileName = file.getOriginalFilename();
                filePath = GlobalVars.BASE_DIR + fileName;
                Path destFile = Paths.get(filePath);
                if(Files.exists(destFile)){
                    throw new Exception("文件已存在");
                }
                Files.copy(file.getInputStream(), destFile);
            }
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
