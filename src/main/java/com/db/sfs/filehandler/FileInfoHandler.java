package com.db.sfs.filehandler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Objects;

public class FileInfoHandler {

    public static boolean isDirPath(String path){
        return new File(path).isDirectory();
    }

    public static long getFileCreateTime(File file) throws IOException {
        return Files.readAttributes(Paths.get(file.getPath()), BasicFileAttributes.class).creationTime().toMillis();
    }

    public static int getDirLength(File file){
        if(file.isDirectory()){
            return Objects.requireNonNull(file.listFiles()).length;
        }
        else {
            return 0;
        }
    }
}
