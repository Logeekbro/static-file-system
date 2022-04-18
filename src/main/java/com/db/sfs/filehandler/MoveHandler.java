package com.db.sfs.filehandler;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class MoveHandler {

    public Path move(String source, String target) throws IOException {
        return Files.move(Paths.get(source), Paths.get(target));
    }
}
