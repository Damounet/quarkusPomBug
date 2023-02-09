package com.toto.tdng.datachecker.common.minio.model;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public final class MinioClient {

    @ConfigProperty(name = "nicos.minio.url")
    String minioUrl;

    @ConfigProperty(name = "nicos.minio.accesskey")
    String minioAccesskey;

    @ConfigProperty(name = "nicos.minio.secretkey")
    String minioSecretkey;

    private io.minio.MinioClient INSTANCE;

    private MinioClient() {
    }

    public io.minio.MinioClient getInstance() {
        if (this.INSTANCE == null) {
            this.INSTANCE = io.minio.MinioClient.builder()
                    .endpoint(this.minioUrl)
                    .credentials(this.minioAccesskey, this.minioSecretkey)
                    .build();
        }
        return this.INSTANCE;
    }

}
