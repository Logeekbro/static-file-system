package com.db.sfs.filehandler;

import com.db.sfs.common.GlobalVars;
import com.db.sfs.entity.HyperFile;

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

    public static boolean exists(String path){return new File(path).exists(); }

    // 判断路径是否为BASE_DIR下的文件或文件夹，防止使用 ../ 操作BASE_DIR之外的文件
    public static boolean inBaseDir(String path) throws IOException {
        HyperFile fod = new HyperFile(path);
        return fod.exists() || fod.getRealPath().startsWith(GlobalVars.BASE_DIR);
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
