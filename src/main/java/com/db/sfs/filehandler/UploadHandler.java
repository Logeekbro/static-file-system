package com.db.sfs.filehandler;

import com.db.sfs.common.GlobalVars;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;

@Component
public class UploadHandler {

    public void uploadMultipartFiles(MultipartFile file, String path) throws Exception {
        String fileName;
        String filePath;
            fileName = file.getOriginalFilename();
            filePath = GlobalVars.BASE_DIR + path + fileName ;
            File destFile = new File(filePath);
            if(destFile.exists()){
                throw new Exception("文件已存在");
            }
            Files.copy(file.getInputStream(), destFile.toPath());
    }
    public boolean uploadMultipartFiles(MultipartFile[] files) {
        String fileName;
        String filePath;
        try{
            for(MultipartFile file : files){
                fileName = file.getOriginalFilename();
                filePath = GlobalVars.BASE_DIR + fileName;
                File distFile = new File(filePath);
                Files.copy(file.getInputStream(), distFile.toPath());
            }
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
