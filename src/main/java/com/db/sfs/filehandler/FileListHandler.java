package com.db.sfs.filehandler;

import com.db.sfs.common.GlobalVars;
import com.db.sfs.entity.DBDir;
import com.db.sfs.entity.DBFile;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class FileListHandler {

    public boolean isDir(String path){
        return new File(path).isDirectory();
    }

    public DBDir getFileList(String path) throws IOException {
        DBDir dbDir = new DBDir();
        File dir = new File(path);
        dbDir.setDirPath(path.replace(GlobalVars.BASE_DIR, ""));
        dbDir.setDirName(dir.getName());
        dbDir.setCreateTime(FileInfoHandler.getFileCreateTime(dir));
        int fileCount = 0;
        // 指定List初始大小，防止多次扩容影响性能
        List<DBFile> fileList = new ArrayList<>(100);
        List<DBDir> dirList = new ArrayList<>(50);
        for(File file : Objects.requireNonNull(dir.listFiles())){
            if(file.isFile()){
                DBFile dbFile = new DBFile();
                dbFile.setFileName(file.getName());
                // 拼接url
                String url = null;
                if(dbDir.getDirPath().equals("")){
                    url = GlobalVars.FILE_HOST + path + file.getName();
                }
                else {
                    url = GlobalVars.FILE_HOST + path + GlobalVars.FILE_SEP + file.getName();
                }
                dbFile.setUrl(url);
                dbFile.setSize(Files.size(file.toPath()));
                dbFile.setCreateTime(FileInfoHandler.getFileCreateTime(file));
                dbFile.setLastModifiedTime(file.lastModified());
                fileList.add(dbFile);
            }
            else {
                DBDir dbDir1 = new DBDir();
                dbDir1.setDirPath(file.getPath().replace(GlobalVars.BASE_DIR, ""));
                dbDir1.setDirName(file.getName());
                dbDir1.setCreateTime(FileInfoHandler.getFileCreateTime(file));
                dbDir1.setFileCount(FileInfoHandler.getDirLength(file));
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
        long start = System.currentTimeMillis();
        FileListHandler fileListHandler = new FileListHandler();

        System.out.println(fileListHandler.getFileList(GlobalVars.BASE_DIR + "AriaNg-1.2.3\\views"));
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + "ms");
    }
}
