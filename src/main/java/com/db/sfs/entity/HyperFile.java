package com.db.sfs.entity;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

// 自定义文件类，为父类增加了createTime和size属性
public class HyperFile extends File {

    private long createTime =
            Files.readAttributes(Paths.get(this.getPath()), BasicFileAttributes.class).creationTime().toMillis();
    private long size = Files.size(this.toPath());

    public HyperFile(String pathname) throws IOException {
        super(pathname);
    }

    public HyperFile(String parent, String child) throws IOException {
        super(parent, child);
    }

    public HyperFile(File parent, String child) throws IOException {
        super(parent, child);
    }

    public HyperFile(URI uri) throws IOException {
        super(uri);
    }

    @Override
    public HyperFile[] listFiles() {
        String[] ss = list();
        if (ss == null) return null;
        int n = ss.length;
        HyperFile[] fs = new HyperFile[n];
        for (int i = 0; i < n; i++) {
            try {
                fs[i] = new HyperFile(this, ss[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fs;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}
