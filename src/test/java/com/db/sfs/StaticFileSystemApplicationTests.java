package com.db.sfs;

import com.db.sfs.filehandler.FileListHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootTest
class StaticFileSystemApplicationTests {



    @Test
    void testMoveFile() throws IOException {
        Path sour = Paths.get("D:\\A_Pro_luangao\\test_dir\\222");
        Path target = Paths.get("D:\\A_Pro_luangao\\test_dir\\1\\222\\");
        Files.move(sour, target);

    }


}
