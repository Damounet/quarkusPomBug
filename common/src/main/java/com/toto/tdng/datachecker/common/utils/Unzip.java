package com.toto.tdng.datachecker.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import com.toto.tdng.datachecker.common.model.exception.PackageProcessingException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Unzip {
    /**
     * Unzip a file locally by writing all the content in the same folder. Only
     * works if the archive doesn't contain folders
     * 
     * @param packageZip Package archive
     * @param storage Path to the local unzip folder
     * @throws PackageProcessingException unable to unzip the package
     */
    public static void unzipFile(File packageZip, String storage) throws PackageProcessingException {

        FileInputStream fis = null;
        ZipInputStream zis = null;
        try {
            // Unzip the archive locally
            fis = new FileInputStream(packageZip);
            zis = new ZipInputStream(fis);
            ZipEntry zipEntry = zis.getNextEntry();

            // Buffer to read archive content
            byte[] buffer = new byte[1024];

            // Each file contained in the archive is an entry
            while (zipEntry != null) {
                // create the file
                File newFile = new File(storage + zipEntry.getName());
                FileOutputStream fos = new FileOutputStream(newFile);

                // insert file content
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
                // close the entry and get the next entry
                zis.closeEntry();
                zipEntry = zis.getNextEntry();
            }
        } catch (FileNotFoundException e) {
            log.error("File missing " + e.getMessage());
            throw new PackageProcessingException("File missing " + e.getMessage());
        } catch (SecurityException e) {
            log.error("Permission denied " + e.getMessage());
            throw new PackageProcessingException("Permission denied " + e.getMessage());
        } catch (IOException e) {
            log.error("Unable to write package locally : " + e.getMessage());
            throw new PackageProcessingException("Unable to write package locally : " + e.getMessage());
        } finally {
            // Close inputStreams if they exist
            if (zis != null) {
                try {
                    zis.closeEntry();
                    zis.close();
                } catch (IOException e) {
                    log.warn("Unable to close ZipInputStream :" + e.getMessage());
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    log.warn("Unable to close PackageInputStream :" + e.getMessage());
                }
            }
            if (packageZip != null) {
                // Clean zip file after use
                packageZip.delete();
            }
        }
    }
}