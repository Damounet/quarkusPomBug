package com.toto.tdng.datachecker.common.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.toto.tdng.datachecker.common.model.exception.TechnicalException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WriteFile {
    /**
     * Write a file locally using the filename and the bytes contained in the file
     * 
     * @param content
     * @param filename
     * @return File
     */
    static public File writeFile(byte[] content, String packagePath) throws TechnicalException {
        File file = new File(packagePath);
        try (FileOutputStream fop = new FileOutputStream(file);) {
            if (!file.exists()) {
                file.createNewFile();
            }
            fop.write(content);

            return file;
        } catch (IOException | SecurityException e) {
            log.error("Fail to write file : " + file.getName() + " : " + e.getMessage());
            throw new TechnicalException("Fail to write file : " + file.getName() + " : " + e.getMessage());
        }
    }


}
