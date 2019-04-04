package net.liuzd.spring.boot.v2.util;

import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AESUtils {

    private static final String ENCODE_UTF8 = "UTF-8";
    private static final String AES         = "AES";
    private static final String SHA1PRNG    = "SHA1PRNG";
    private static final int    KG      = 128;

    public static String encrypt2Hex(String content, String password) throws Exception {
        byte[] encryptResult = encrypt(content, password);
        return parseByte2HexStr(encryptResult);
    }

    public static String decryptHex(String content, String password) throws Exception {
        byte[] decryptResult = decrypt(parseHexStr2Byte(content), password);
        return new String(decryptResult, ENCODE_UTF8);
    }

    public static String parseByte2HexStr(byte[] buf) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; ++i) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1) {
            return null;
        }
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; ++i) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    private static byte[] encrypt(String content, String password) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance(AES);
        SecureRandom secureRandom = SecureRandom.getInstance(SHA1PRNG);
        secureRandom.setSeed(password.getBytes());
        kgen.init(KG, secureRandom);
        SecretKey secretKey = kgen.generateKey();
        byte[] enCodeFormat = secretKey.getEncoded();
        SecretKeySpec key = new SecretKeySpec(enCodeFormat, AES);

        Cipher cipher = Cipher.getInstance(AES);
        byte[] byteContent = content.getBytes(ENCODE_UTF8);

        cipher.init(1, key);

        byte[] result = cipher.doFinal(byteContent);
        return result;
    }

    private static byte[] decrypt(byte[] content, String password) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance(AES);
        SecureRandom secureRandom = SecureRandom.getInstance(SHA1PRNG);
        secureRandom.setSeed(password.getBytes());
        kgen.init(KG, secureRandom);
        SecretKey secretKey = kgen.generateKey();
        byte[] enCodeFormat = secretKey.getEncoded();
        SecretKeySpec key = new SecretKeySpec(enCodeFormat, AES);
        Cipher cipher = Cipher.getInstance(AES);
        cipher.init(2, key);
        byte[] result = cipher.doFinal(content);
        return result;
    }
}