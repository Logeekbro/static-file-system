package com.db.sfs.test;

import com.db.sfs.common.GlobalVars;
import com.db.sfs.entity.HyperFile;
import com.db.sfs.filehandler.FileInfoHandler;
import com.db.sfs.filehandler.FileListHandler;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;


class FileTest {




    public static void main(String[] args) throws IOException {
        String path = GlobalVars.BASE_DIR +  "oss-browser-win32-x64";
        HyperFile dir = new HyperFile(path);
        long start = System.currentTimeMillis();
//        System.out.println(dir.deleteDir());
        System.out.println("耗时：" + (System.currentTimeMillis() - start));
    }


}
