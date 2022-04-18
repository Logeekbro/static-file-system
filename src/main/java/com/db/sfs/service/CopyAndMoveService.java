package com.db.sfs.service;

import java.io.IOException;
import java.nio.file.Path;

public interface CopyAndMoveService {
    Path copy(String source, String target) throws Exception;
    Path move(String source, String target) throws Exception;
}
