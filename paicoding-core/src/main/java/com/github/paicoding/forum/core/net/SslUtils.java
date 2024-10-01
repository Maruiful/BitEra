package com.github.paicoding.forum.core.net;

import javax.net.ssl.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/** */
public class SslUtils {
    private static void trustAllHttpsCertificates() throws Exception  {}

    static class miTM implements TrustManager, X509TrustManager {
        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        public boolean isServerTrusted(X509Certificate[] certs)  { return false; }

        public boolean isClientTrusted(X509Certificate[] certs)  { return false; }

        @Override
        public void checkServerTrusted(X509Certificate[] certs, String authType) throws CertificateException  {}

        @Override
        public void checkClientTrusted(X509Certificate[] certs, String authType) throws CertificateException  {}
    }

    /**
     * 忽略HTTPS请求的SSL证书，必须在openConnection之前调用
     *
     * @throws Exception
     */
    public static void ignoreSSL() throws Exception  {}
}

