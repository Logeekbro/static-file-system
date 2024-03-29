package com.db.sfs.filehandler;

import com.db.sfs.common.GlobalVars;
import com.db.sfs.entity.DBDir;
import com.db.sfs.entity.DBFile;
import com.db.sfs.entity.HyperFile;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.LinkedList;
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
        List<DBFile> fileList = new LinkedList<>();
        List<DBDir> dirList = new LinkedList<>();
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

    public DBDir getLargeFileList(String path) throws Exception {
        DBDir dbDir = new DBDir();
        Path dir = Paths.get(path);
        dbDir.setDirPath(dir.toRealPath().toString().replace(GlobalVars.BASE_DIR, ""));
        dbDir.setDirName(dir.getFileName().toString());
        dbDir.setCreateTime(Files.readAttributes(dir, BasicFileAttributes.class).creationTime().toMillis());
        dbDir.setLastModifiedTime(Files.getLastModifiedTime(dir).toMillis());
        dbDir.setFileCount((int) Files.list(dir).count());
        List<DBFile> fileList = new LinkedList<>();
        List<DBDir> dirList = new LinkedList<>();
        Files.list(dir).forEach(
                fod -> {
                    // 对文件进行操作
                    if(!Files.isDirectory(fod)){
                        DBFile dbFile = new DBFile();
                        try{
                            dbFile.setFileName(fod.getFileName().toString());
                            // 拼接url
                            String url = GlobalVars.FILE_HOST + fod.toRealPath();
                            dbFile.setUrl(url);
                            dbFile.setSize(Files.size(fod));
                            dbFile.setCreateTime(Files.readAttributes(fod, BasicFileAttributes.class).creationTime().toMillis());
                            dbFile.setLastModifiedTime(Files.getLastModifiedTime(fod).toMillis());
                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }
                        fileList.add(dbFile);
                    }
                    // 对子目录进行操作
                    else {
                        DBDir dbDir1 = new DBDir();
                        try{
                            dbDir1.setDirPath(fod.toRealPath().toString().replace(GlobalVars.BASE_DIR, ""));
                            dbDir1.setDirName(fod.getFileName().toString());
                            dbDir1.setCreateTime(Files.readAttributes(fod, BasicFileAttributes.class).creationTime().toMillis());
                            dbDir1.setLastModifiedTime(Files.getLastModifiedTime(fod).toMillis());
                            dbDir1.setFileCount((int) Files.list(fod).count());
                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }

                        dirList.add(dbDir1);
                    }
                }
        );
        dbDir.setFiles(fileList);
        dbDir.setDirs(dirList);
        return dbDir;
    }

    public static void main(String[] args) throws Exception {

        FileListHandler fileListHandler = new FileListHandler();
        long start = System.currentTimeMillis();
        System.out.println(fileListHandler.getFileList(GlobalVars.BASE_DIR + ""));
//        System.out.println(fileListHandler.getLargeFileList(GlobalVars.BASE_DIR + ""));
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + "ms");
    }
}
