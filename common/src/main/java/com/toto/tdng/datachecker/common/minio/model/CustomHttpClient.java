package com.toto.tdng.datachecker.common.minio.model;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;

@Slf4j
public class CustomHttpClient {

    public static OkHttpClient getUnsafeOkHttpClient() {
        try {
            log.debug("Creating a custom Http Client");

            final TrustManager[] trustAllCerts = new TrustManager[] {
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(X509Certificate[] x509Certificates, String s)
                                throws CertificateException {

                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] x509Certificates, String s)
                                throws CertificateException {

                        }

                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[] {};
                        }
                    }
            };

            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new SecureRandom());

            OkHttpClient.Builder newBuilder = new OkHttpClient.Builder();
            newBuilder.sslSocketFactory(sslContext.getSocketFactory(), (X509TrustManager) trustAllCerts[0]);
            newBuilder.hostnameVerifier((hostname, session) -> true);
            return newBuilder.build();
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            log.error("Encountered error while creating Custom Http Client : " + e);
        }
        return null;
    }
}
