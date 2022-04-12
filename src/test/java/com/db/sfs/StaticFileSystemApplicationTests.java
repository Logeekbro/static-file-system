package com.db.sfs;

import com.db.sfs.filehandler.FileListHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class StaticFileSystemApplicationTests {

    @Autowired
    FileListHandler fileListHandler;

    @Test
    void contextLoads() throws IOException {
        System.out.println(fileListHandler.getFileList(""));
    }

}
