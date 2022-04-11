package com.db.sfs.entity;

public class DBFile {
    private String fileName;
    private String url;
    private final String type = "file";
    private long size;
    private long createTime;
    private long lastModifiedTime;

    public DBFile() {
    }

    public DBFile(String fileName, String url, long size, long createTime, long lastModifiedTime) {
        this.fileName = fileName;
        this.url = url;
        this.size = size;
        this.createTime = createTime;
        this.lastModifiedTime = lastModifiedTime;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(long lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }


    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "DBFile{" +
                "fileName='" + fileName + '\'' +
                ", url='" + url + '\'' +
                ", type='" + type + '\'' +
                ", size=" + size +
                ", createTime=" + createTime +
                ", lastModifiedTime=" + lastModifiedTime +
                '}';
    }
}
