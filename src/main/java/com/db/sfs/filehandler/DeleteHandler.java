package com.db.sfs.filehandler;

import com.db.sfs.entity.HyperFile;
import org.springframework.stereotype.Component;

@Component
public class DeleteHandler {


    public boolean deleteFile(String path) {
        HyperFile file = new HyperFile(path);
        if(file.exists() && file.isFile()){
            return file.delete();
        }
        else {
            return false;
        }
    }

    public boolean deleteDir(String path){
        HyperFile dir = new HyperFile(path);
        if(dir.exists() && dir.isDirectory()){
            return dir.deleteDir();
        }
        else {
            return false;
        }
    }
}
