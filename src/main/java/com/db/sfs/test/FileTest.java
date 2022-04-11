package com.db.sfs.test;

import com.db.sfs.filehandler.ListFileHandler;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;


class FileTest {

    @Autowired
    ListFileHandler listFileHandler;

    @Test
    public void test1() throws IOException {
        System.out.println(listFileHandler.getFileList(""));

    }

}
