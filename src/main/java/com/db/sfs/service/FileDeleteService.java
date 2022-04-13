package com.db.sfs.service;


public interface FileDeleteService {

    void deleteFile(String path) throws Exception;

    void deleteDir(String path) throws Exception;
}
