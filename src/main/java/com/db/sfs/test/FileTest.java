package com.db.sfs.test;

import com.db.sfs.entity.HyperFile;
import com.db.sfs.filehandler.FileListHandler;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;


class FileTest {


    public static void main(String[] args) throws IOException {
        String path = "D:\\BaiduYunDownload\\服务器启动.bat";
        HyperFile file = new HyperFile(path);
        System.out.println(file.getCreateTime());
    }
    @Test
    public void test1() throws IOException {
        String path = "D:\\BaiduYunDownload\\服务器启动.bat";
        HyperFile file = new HyperFile(path);
        System.out.println(file.getCreateTime());

    }

}
