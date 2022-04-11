package com.db.sfs;

import com.db.sfs.filehandler.ListFileHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class StaticFileSystemApplicationTests {

    @Autowired
    ListFileHandler listFileHandler;

    @Test
    void contextLoads() throws IOException {
        System.out.println(listFileHandler.getFileList(""));
    }

}
