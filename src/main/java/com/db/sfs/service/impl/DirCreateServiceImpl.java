package com.db.sfs.service.impl;

import com.db.sfs.common.GlobalVars;
import com.db.sfs.filehandler.DeleteHandler;
import com.db.sfs.filehandler.DirCreateHandler;
import com.db.sfs.filehandler.FileInfoHandler;
import com.db.sfs.service.DirCreateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DirCreateServiceImpl implements DirCreateService {

    @Autowired
    private DirCreateHandler dirCreateHandler;


    @Override
    public void createDir(String path) throws Exception {
        path = GlobalVars.BASE_DIR + path;
        if(FileInfoHandler.exists(path)){
            throw new Exception("文件夹已存在！");
        }
        try{
            dirCreateHandler.createDir(path);
            if(!FileInfoHandler.inBaseDir(path)){
                DeleteHandler deleteHandler = new DeleteHandler();
                deleteHandler.deleteDir(path);
                throw new Exception("创建失败！");
            }
        }
        catch (Exception e){
            throw new Exception("创建失败！");
        }

    }
}
