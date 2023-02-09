package com.toto.tdng.datachecker.common.minio.exception;

public class MinioTechnicalException extends Exception{

    public MinioTechnicalException(String errorMessage) {
        super(errorMessage);
    }

}
