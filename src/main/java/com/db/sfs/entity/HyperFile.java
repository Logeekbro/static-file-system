package com.db.sfs.entity;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Objects;

// 自定义文件类，为父类增加了createTime和size属性
public class HyperFile extends File {

    private long createTime;
    private long size;

    public HyperFile(String pathname) {
        super(pathname);
    }

    public HyperFile(String parent, String child) {
        super(parent, child);
    }

    public HyperFile(File parent, String child) {
        super(parent, child);
    }

    public HyperFile(URI uri) {
        super(uri);
    }

    @Override
    public HyperFile[] listFiles() {
        String[] ss = list();
        if (ss == null) return null;
        int n = ss.length;
        HyperFile[] fs = new HyperFile[n];
        for (int i = 0; i < n; i++) {
            fs[i] = new HyperFile(this, ss[i]);
        }
        return fs;
    }
    // 当HyperFile实例为一个文件夹时，调用此方法删除该文件夹及所有子目录和文件
    public boolean deleteDir(){
        if(this.exists() && this.isDirectory()){
            // 遍历文件夹下的文件
            for(HyperFile fad : this.listFiles()){
                if(fad.isFile()){
                    fad.delete();
                }
                else if(fad.isDirectory()){
                    fad.deleteDir();
                }
            }
            // 删除子文件后把当前文件夹也删除
            this.delete();
            return true;
        }else{
            return false;
        }
    }

    public long getCreateTime() throws IOException {
         this.createTime =
                 Files.readAttributes(Paths.get(this.getPath()), BasicFileAttributes.class).creationTime().toMillis();
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getSize() throws IOException {
        this.size  = Files.size(this.toPath());
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}
