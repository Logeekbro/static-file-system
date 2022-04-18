package com.db.sfs.service.impl;

import com.db.sfs.filehandler.CopyHandler;
import com.db.sfs.filehandler.MoveHandler;
import com.db.sfs.service.CopyAndMoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.FileAlreadyExistsException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

@Service
public class CopyAndMoveServiceImpl implements CopyAndMoveService {

    @Autowired
    private MoveHandler moveHandler;

    @Autowired
    private CopyHandler copyHandler;

    @Override
    public Path copy(String source, String target) throws Exception {
        try{
            return copyHandler.copy(source, target);
        }
        catch (NoSuchFileException e){
            throw new Exception("文件不存在或已被删除");
        }
        catch (FileAlreadyExistsException e){
            throw new Exception("目标目录存在同名文件");
        }
        catch (Exception e){
            throw e;
        }


    }

    @Override
    public Path move(String source, String target) throws Exception {
        try{
            return moveHandler.move(source, target);
        }
        catch (NoSuchFileException e){
            throw new Exception("文件不存在或已被删除");
        }
        catch (FileAlreadyExistsException e){
            throw new Exception("目标目录存在同名文件");
        }
        catch (Exception e){
            throw e;
        }
    }
}
