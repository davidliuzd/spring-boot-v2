package net.liuzd.spring.boot.v2.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class MD5Utils {

    private static final int DEFAULT_BYTE_SIZE = 1024;
    private static final int MD5_16            = 16;
    private static final int MD5_32            = 32;

    public static String encrypt(String val) {
        byte[] digestBytes = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(val.getBytes("UTF-8"));
            digestBytes = md.digest();
        } catch (NoSuchAlgorithmException e) {
            log.error("encryptToMD5 NoSuchAlgorithmException error", e);
        } catch (UnsupportedEncodingException e) {
            log.error("encryptToMD5 UnsupportedEncodingException(UTF-8) error", e);
        }
        if (null != digestBytes) {
            return byte2hex(digestBytes);
        }
        return "";
    }

    public static String getFile(File file) {
        if (!file.isFile()) {
            return null;
        }
        MessageDigest digest = null;
        FileInputStream in = null;
        byte[] buffer = new byte[DEFAULT_BYTE_SIZE];
        try {
            digest = MessageDigest.getInstance("MD5");
            in = new FileInputStream(file);
            int size = 0;
            while ((size = in.read(buffer, 0, DEFAULT_BYTE_SIZE)) != -1) {
                digest.update(buffer, 0, size);
            }
        } catch (Exception e) {
            log.error("getFileMD5 error", e);
            return null;
        } finally {
            try {
                if (null != in) in.close();
            } catch (IOException e) {
                log.error("close InputStream error", e);
            }
        }
        BigInteger bigInt = new BigInteger(1, digest.digest());
        String md5STr = bigInt.toString(MD5_16);
        while (md5STr.length() < MD5_32) {
            md5STr = "0" + md5STr;
        }
        return md5STr;
    }

    private static String byte2hex(byte[] b) {
        StringBuffer hs = new StringBuffer();
        String stmp = "";
        for (int n = 0; n < b.length; ++n) {
            stmp = Integer.toHexString(b[n] & 0xFF);
            if (stmp.length() == 1)
                hs.append("0").append(stmp);
            else {
                hs.append(stmp);
            }
        }
        return hs.toString().toUpperCase();
    }
}