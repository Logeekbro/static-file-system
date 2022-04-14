package com.db.sfs.filehandler;

import com.db.sfs.common.GlobalVars;
import com.db.sfs.entity.DBDir;
import com.db.sfs.entity.DBFile;
import com.db.sfs.entity.HyperFile;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class FileListHandler {



    public DBDir getFileList(String path) throws IOException {
        DBDir dbDir = new DBDir();
        HyperFile dir = new HyperFile(path);
        dbDir.setDirPath(dir.getRealPath().replace(GlobalVars.BASE_DIR, ""));
        dbDir.setDirName(dir.getName());
        dbDir.setCreateTime(dir.getCreateTime());
        dbDir.setLastModifiedTime(dir.lastModified());
        dbDir.setFileCount(FileInfoHandler.getDirLength(dir));
        // 指定List初始大小，防止多次扩容影响性能
        List<DBFile> fileList = new ArrayList<>(100);
        List<DBDir> dirList = new ArrayList<>(20);
        for(HyperFile file : Objects.requireNonNull(dir.listFiles())){
            // 对文件进行操作
            if(file.isFile()){
                DBFile dbFile = new DBFile();
                dbFile.setFileName(file.getName());
                // 拼接url
                String url = GlobalVars.FILE_HOST + file.getRealPath();
                dbFile.setUrl(url);
                dbFile.setSize(file.getSize());
                dbFile.setCreateTime(file.getCreateTime());
                dbFile.setLastModifiedTime(file.lastModified());
                fileList.add(dbFile);
            }
            // 对子目录进行操作
            else {
                DBDir dbDir1 = new DBDir();
                dbDir1.setDirPath(file.getRealPath().replace(GlobalVars.BASE_DIR, ""));
                dbDir1.setDirName(file.getName());
                dbDir1.setCreateTime(file.getCreateTime());
                dbDir1.setLastModifiedTime(file.lastModified());
                dbDir1.setFileCount(FileInfoHandler.getDirLength(file));
                dirList.add(dbDir1);
            }
        }
        dbDir.setFiles(fileList);
        dbDir.setDirs(dirList);
        return dbDir;
    }

    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        FileListHandler fileListHandler = new FileListHandler();

        System.out.println(fileListHandler.getFileList(GlobalVars.BASE_DIR + ""));
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + "ms");
    }
}
