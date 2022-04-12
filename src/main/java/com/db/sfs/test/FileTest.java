package com.db.sfs.test;

import com.db.sfs.filehandler.FileListHandler;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;


class FileTest {

    @Autowired
    FileListHandler fileListHandler;

    @Test
    public void test1() throws IOException {
        System.out.println(fileListHandler.getFileList(""));

    }

}
