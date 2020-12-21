package com.example.demo.Common.utils;


public class ByteUtil {

    /**
     * 转换成16进制表示的大写字符串
     *
     * @param bts 被转换的byte数组
     * @return 16进制表示的字符串
     */
    public static String byte2HexUpperCase(byte[] bts) {

        String tmp = null;
        StringBuffer des = new StringBuffer();
        for (int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1) {
                des.append("0");
            }
            des.append(tmp);
        }
        return des.toString().toUpperCase();
    }

    /**
     * 转换成16进制表示的小写字符串
     *
     * @param bts 被转换的byte数组
     * @return 16进制表示的字符串
     */
    public static String byte2HexLowerCase(byte[] bts) {
        StringBuffer des = new StringBuffer();
        String tmp = null;
        for (int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1) {
                des.append("0");
            }
            des.append(tmp);
        }
        return des.toString().toLowerCase();
    }


}
