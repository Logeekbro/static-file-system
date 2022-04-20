package com.db.sfs.entity;

import java.io.Serializable;
import java.util.List;

public class DBDir implements Serializable {
    private String dirName;
    private String dirPath;
    private final String type = "dir";
    private int fileCount;
    private long createTime;
    private long lastModifiedTime;
    private List<DBFile> files;
    private List<DBDir> dirs;

    public DBDir() {
    }

    public DBDir(String dirName, String dirPath, int fileCount, long createTime, long lastModifiedTime, List<DBFile> files, List<DBDir> dirs) {
        this.dirName = dirName;
        this.dirPath = dirPath;
        this.fileCount = fileCount;
        this.createTime = createTime;
        this.lastModifiedTime = lastModifiedTime;
        this.files = files;
        this.dirs = dirs;
    }

    public String getDirName() {
        return dirName;
    }

    public void setDirName(String dirName) {this.dirName = dirName; }

    public String getDirPath() {
        return dirPath;
    }

    public void setDirPath(String dirPath) {
        this.dirPath = dirPath;
    }

    public long getFileCount() {
        return fileCount;
    }

    public void setFileCount(int fileCount) {
        this.fileCount = fileCount;
    }

    public List<DBFile> getFiles() {
        return files;
    }

    public void setFiles(List<DBFile> files) {
        this.files = files;
    }

    public List<DBDir> getDirs() {
        return dirs;
    }

    public void setDirs(List<DBDir> dirs) {
        this.dirs = dirs;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getType() {
        return type;
    }

    public long getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(long lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    @Override
    public String toString() {
        return "DBDir{" +
                "dirName='" + dirName + '\'' +
                ", dirPath='" + dirPath + '\'' +
                ", type='" + type + '\'' +
                ", fileCount=" + fileCount +
                ", createTime=" + createTime +
                ", lastModifiedTime=" + lastModifiedTime +
                ", files=" + files +
                ", dirs=" + dirs +
                '}';
    }
}
