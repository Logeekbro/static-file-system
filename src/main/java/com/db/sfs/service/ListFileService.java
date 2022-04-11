package com.db.sfs.service;

import com.db.sfs.entity.DBDir;

public interface ListFileService {
    DBDir getFileList(String path) throws Exception;
}
