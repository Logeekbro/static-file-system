package com.db.sfs.service.impl;

import com.db.sfs.common.GlobalVars;
import com.db.sfs.entity.DBDir;
import com.db.sfs.filehandler.FileListHandler;
import com.db.sfs.service.FileListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileListServiceImpl implements FileListService {

    @Autowired
    FileListHandler fileListHandler;

    @Override
    public DBDir getFileList(String path) throws Exception {
        if(!fileListHandler.isDir(GlobalVars.BASE_DIR + path)){
            throw new Exception("路径有误");
        }
        return fileListHandler.getFileList(GlobalVars.BASE_DIR + path);
    }
}