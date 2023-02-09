package com.toto.tdng.datachecker.common.minio;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.toto.tdng.datachecker.common.minio.exception.MinioTechnicalException;
import com.toto.tdng.datachecker.common.minio.model.MinioClient;
import com.toto.tdng.datachecker.common.referentials.model.ReferentialsResponse;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import io.minio.GetObjectArgs;
import io.minio.UploadObjectArgs;
import io.minio.errors.MinioException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
public class MinioService {

    @Inject
    MinioClient minioClientSingleton;

    @ConfigProperty(name = "nicos.minio.referentialsMinioPath")
    String referentialsMinioPath;

    @ConfigProperty(name = "nicos.minio.deliveryBucket")
    String deliveryBucket;

    public ReferentialsResponse getRefenrentialsObject(String key) throws MinioException {
        String resultSting = "";
        try {
            io.minio.MinioClient minioClient = minioClientSingleton.getInstance();
            InputStream stream = minioClient.getObject(
                    GetObjectArgs.builder()
                            .bucket(this.deliveryBucket)
                            .object(this.referentialsMinioPath + "/" + key).build());

            byte[] buf = new byte[16384];
            int bytesRead;
            while ((bytesRead = stream.read(buf, 0, buf.length)) >= 0) {
                resultSting += new String(buf, 0, bytesRead, StandardCharsets.UTF_8);
            }
            stream.close();
            ReferentialsResponse referentialsResponse = new ReferentialsResponse(key, resultSting);
            log.info("Fetched doc : " + referentialsResponse.getName());
            return referentialsResponse;
        } catch (MinioException | IOException | InvalidKeyException | NoSuchAlgorithmException e) {
            log.error("Minio error occurred while fetching : " + e.getMessage());
            throw new MinioException(e.getMessage());
        }
    }

    /**
     * Upload the content of a directory to Minio
     * 
     * @param targetDirectory 
     * @param deliveryId
     * @param bucketId
     * @throws MinioTechnicalException Error on the upload to minio
     */
    public void uploadDirectoryContent(File targetDirectory, String deliveryId, String bucketId) throws MinioTechnicalException {
        String minioPath = deliveryId;

        // Take the list of files contained in the directory and upload each file
        File[] listStorage = targetDirectory.listFiles();
        for (File content : listStorage) {
            uploadToMinio(bucketId, minioPath, content);
        }
        log.info("Processing and uploading of the package " + deliveryId + " was Successful");
    }

    /**
     * Upload the file to the designated minio Bucket
     * 
     * @param file
     * @param bucketId
     * @throws MinioTechnicalException
     */
    public void uploadToMinio(String bucketId, String minioPath, File file)
            throws MinioTechnicalException {
        // Upload the selected file in the selected minio Bucket
        try {
            io.minio.MinioClient minioClient = minioClientSingleton.getInstance();
            minioClient.uploadObject(
                    UploadObjectArgs.builder()
                            .bucket(bucketId)
                            .object(minioPath + "/" + file.getName())
                            .filename(file.getAbsolutePath())
                            .build());
            log.info("Successfully uploaded : " + file.getName() + " to Minio");
        } catch (MinioException | InvalidKeyException | NoSuchAlgorithmException | IOException e) {
            log.error("Minio error occurred while uploading package : " + file.getName() + " - " + e.getMessage(), e);
            throw new MinioTechnicalException(
                    "Minio error occurred while uploading package : " + file.getName() + " - " + e.getMessage());
        }
    }

}
