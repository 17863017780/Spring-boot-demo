package com.example.demo.Common.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Md5Util {
    private static final Logger logger = LoggerFactory.getLogger(Md5Util.class);

    private final static String UTF8 = "UTF-8";
    private final static String MD5 = "MD5";

    /**
     * @param saltHead 盐头,可空 UTF-8 编码
     * @param str      需要md5的字符串 UTF-8 编码
     * @param saltTail 盐尾,可空 UTF-8 编码
     * @return 32位MD5小写
     */
    public static String md5Lower32(String saltHead, String str, String saltTail) {

        try {
            if (saltHead == null) {
                saltHead = "";
            }
            if (saltTail == null) {
                saltTail = "";
            }
            byte[] bytes = (saltHead + str + saltTail).getBytes(UTF8);
            MessageDigest messageDigest = MessageDigest.getInstance(MD5);
            messageDigest.update(bytes);
            bytes = messageDigest.digest();
            return ByteUtil.byte2HexLowerCase(bytes);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            return null;
        }
    }

    /**
     * @param saltHead 盐头,可空
     * @param str      需要md5的字符串
     * @param saltTail 盐尾,可空
     * @return 32位MD5大写
     */
    public static String md5Upper32(String saltHead, String str, String saltTail) {
        String result;
        try {
            if (saltHead == null) {
                saltHead = "";
            }
            if (saltTail == null) {
                saltTail = "";
            }
            byte[] bytes = (saltHead + str + saltTail).getBytes(UTF8);
            MessageDigest messageDigest = MessageDigest.getInstance(MD5);
            messageDigest.update(bytes);
            bytes = messageDigest.digest();
            result = ByteUtil.byte2HexUpperCase(bytes);
        } catch (Exception e) {
            return null;
        }
        return result;
    }

    public static String md5Lower16(String str) {
        String result;
        try {
            MessageDigest md = MessageDigest.getInstance(MD5);
            md.update(str.getBytes(UTF8));
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            result = buf.toString().substring(8, 24);
        } catch (Exception e) {
            return "";
        }
        return result == null ? "" : result.toLowerCase();
    }

    public static String md5Upper16(String str) {
        String result = null;
        try {
            MessageDigest md = MessageDigest.getInstance(MD5);
            md.update(str.getBytes(UTF8));
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            result = buf.toString().substring(8, 24);
        } catch (Exception e) {
        }
        return result == null ? "" : result.toUpperCase();
    }


}
