package com.db.sfs.service.impl;

import com.db.sfs.common.GlobalVars;
import com.db.sfs.filehandler.DeleteHandler;
import com.db.sfs.filehandler.FileInfoHandler;
import com.db.sfs.service.FileDeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileDeleteServiceImpl implements FileDeleteService {

    @Autowired
    private DeleteHandler deleteHandler;

    @Override
    public void deleteFile(String path) throws Exception {
        path = GlobalVars.BASE_DIR + path.replace(GlobalVars.FILE_HOST, "");
        if(FileInfoHandler.inBaseDir(path)){
            deleteHandler.deleteFile(path);
        }
        else{
            throw new Exception("路径错误");
        }
    }

    @Override
    public void deleteDir(String path) throws Exception {
        path = GlobalVars.BASE_DIR + path;
        if(FileInfoHandler.inBaseDir(path)){
            deleteHandler.deleteDir(path);
        }
        else{
            throw new Exception("路径错误");
        }
    }
}
