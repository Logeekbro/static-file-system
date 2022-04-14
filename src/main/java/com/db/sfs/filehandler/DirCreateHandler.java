package com.db.sfs.filehandler;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class DirCreateHandler {

    public void createDir(String path) throws IOException {
        Files.createDirectories(Paths.get(path));
    }
}
