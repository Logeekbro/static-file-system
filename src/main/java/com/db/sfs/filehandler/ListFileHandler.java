package com.db.sfs.filehandler;

import com.db.sfs.common.GlobalVars;
import com.db.sfs.entity.DBDir;
import com.db.sfs.entity.DBFile;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class ListFileHandler {

    public boolean isDir(String path){
        return new File(path).isDirectory();
    }

    public DBDir getFileList(String path) throws IOException {
        DBDir dbDir = new DBDir();
        File dir = new File(path);
        dbDir.setDirPath(path);
        dbDir.setDirName(dir.getName());
        dbDir.setCreateTime(Files.readAttributes(Paths.get(path), BasicFileAttributes.class).creationTime().toMillis());
        int fileCount = 0;
        List<DBFile> fileList = new ArrayList<>(300);
        List<DBDir> dirList = new ArrayList<>(200);
        for(File file : Objects.requireNonNull(dir.listFiles())){
            if(file.isFile()){
                DBFile dbFile = new DBFile();
                dbFile.setFileName(file.getName());
                String url = GlobalVars.FILE_HOST + path + GlobalVars.FILE_SEP + file.getName();
                url = url.replace(GlobalVars.BASE_DIR, "").replace("\\", "/");
                dbFile.setUrl(url);
                dbFile.setSize(Files.size(file.toPath()));
                dbFile.setCreateTime(Files.readAttributes(Paths.get(file.getPath()), BasicFileAttributes.class).creationTime().toMillis());
                dbFile.setLastModifiedTime(file.lastModified());
                fileList.add(dbFile);
            }
            else {
                DBDir dbDir1 = new DBDir();
                dbDir1.setDirPath(file.getPath().replace(GlobalVars.BASE_DIR, ""));
                dbDir1.setDirName(file.getName());
                dbDir1.setCreateTime(Files.readAttributes(Paths.get(file.getPath()), BasicFileAttributes.class).creationTime().toMillis());
                dbDir1.setFileCount(Objects.requireNonNull(file.listFiles()).length);
                dirList.add(dbDir1);
            }
            fileCount++;
        }
        dbDir.setFiles(fileList);
        dbDir.setDirs(dirList);
        dbDir.setFileCount(fileCount);
        return dbDir;
    }

    public static void main(String[] args) throws IOException {
        ListFileHandler listFileHandler = new ListFileHandler();
        System.out.println(listFileHandler.getFileList(GlobalVars.BASE_DIR + "AriaNg-1.2.3\\fonts"));
    }
}
