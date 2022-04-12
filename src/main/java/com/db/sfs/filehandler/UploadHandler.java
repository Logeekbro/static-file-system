package com.db.sfs.filehandler;

import com.db.sfs.common.GlobalVars;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;

@Component
public class UploadHandler {

    public boolean uploadMultipartFiles(MultipartFile file) {
        String fileName;
        String filePath;
        try{
            fileName = file.getOriginalFilename();
            filePath = GlobalVars.BASE_DIR + fileName;
            File distFile = new File(filePath);
            if(distFile.exists()){
                throw new Exception("文件已存在");
            }
            Files.copy(file.getInputStream(), distFile.toPath());
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
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
