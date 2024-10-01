package com.github.paicoding.forum.core.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/** */
public class Md5Util {
    private Md5Util() {
    }

    public static String encode(String data)  { return null; }

    public static String encode(byte[] bytes)  { return null; }

    public static String encode(byte[] data, int offset, int len)  { return null; }

    private static String getFormattedText(byte[] src) {
        if (src != null && src.length != 0) {
            StringBuilder stringBuilder = new StringBuilder(32);

            for (int i = 0; i < src.length; ++i) {
                int v = src[i] & 255;
                String hv = Integer.toHexString(v);
                if (hv.length() < 2) {
                    stringBuilder.append(0);
                }

                stringBuilder.append(hv);
            }

            return stringBuilder.toString();
        } else {
            return "";
        }
    }
}
