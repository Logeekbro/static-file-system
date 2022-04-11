package com.db.sfs.service.impl;

import com.db.sfs.entity.DBDir;
import com.db.sfs.filehandler.ListFileHandler;
import com.db.sfs.service.ListFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ListFileServiceImpl implements ListFileService {

    @Autowired
    ListFileHandler listFileHandler;

    @Override
    public DBDir getFileList(String path) throws Exception {
        if(!listFileHandler.isDir(path)){
            throw new Exception("路径必须为目录");
        }
        return listFileHandler.getFileList(path);
    }
}
