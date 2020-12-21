//package com.example.demo.Common.utils;
//
////import org.apache.commons.lang3.StringUtils;
//
//import org.junit.platform.commons.util.StringUtils;
//
///**
// * 敏感信息掩码工具
// *
// * @author wujingdao
// * @date 2020/4/10 16:17
// */
//public class DisplayUtil {
//
//    /**
//     * 手机号显示首3末4位，中间用*号隐藏代替，如：138****4213
//     *
//     * @param mobile
//     * @return
//     */
//    public static String displayMobile(String mobile) {
//        if (StringUtils.isBlank(mobile) || mobile.length() <= 8) {
//            return mobile;
//        }
//        return wordMask(mobile, 3, 4, "*");
//    }
//
//    /**
//     * 身份证号显示首1末1位，中间用*号隐藏代替，如：4***********2
//     *
//     * @param certNo
//     * @return
//     */
//    public static String displayCertNo(String certNo) {
//        if (StringUtils.isBlank(certNo)) {
//            return certNo;
//        }
//
//        return wordMask(certNo, 1, 1, "*");
//    }
//
//    /**
//     * 三个字掩码，如：张晓明 如：张*明
//     * 两个字掩码，如：小明 如：*明
//     * 多个字掩码，如：张小明明 如：张**明
//     *
//     * @param name
//     * @return
//     */
//    public static String displayName(String name) {
//        if (StringUtils.isBlank(name) || name.length() == 1) {
//            return name;
//        }
//        if (name.length() == 2) {
//            return "*" + name.substring(1, 2);
//        }
//
//        return wordMask(name, 0, 1, "*");
//    }
//
//    /**
//     * Cvv全隐藏，如： ***
//     *
//     * @param cvv
//     * @return
//     */
//    public static String displayCvv(String cvv) {
//        if (StringUtils.isBlank(cvv)) {
//            return cvv;
//        }
//        return "***";
//    }
//
//    /**
//     * 邮箱像是前两位及最后一位字符，及@后邮箱域名信息，如：ye****y@163.com
//     *
//     * @param email
//     * @return
//     */
//    public static String displayEmail(String email) {
//        if (StringUtils.isBlank(email)) {
//            return email;
//        }
//        String[] temp = email.split("@");
//
//        return wordMask(temp[0], 1, 1, "*") + "@" + temp[1];
//    }
//
//
//    /**
//     * 对字符串进行脱敏处理 --
//     *
//     * @param word        被脱敏的字符
//     * @param startLength 被保留的开始长度 前余n位
//     * @param endLength   被保留的结束长度 后余n位
//     * @param pad         填充字符
//     */
//    public static String wordMask(String word, int startLength, int endLength, String pad) {
//        if (startLength + endLength > word.length()) {
//            return org.apache.commons.lang3.StringUtils.leftPad("", word.length() - 1, pad);
//        }
//        String startStr = word.substring(0, startLength);
//        String endStr = word.substring(word.length() - endLength, word.length());
//        return startStr + StringUtils.leftPad("", word.length() - startLength - endLength, pad) + endStr;
//    }
//
//    public static void main(String[] args) {
//        String name = "张三";
//        String mobile = "185100000001";
//        String certNo = "421010202004101102";
//        String email = "demo2020@163.com";
//        System.out.println("姓名:" + displayName(name));
//        System.out.println("手机:" + displayMobile(mobile));
//        System.out.println("证件号:" + displayCertNo(certNo));
//        System.out.println("邮箱:" + displayEmail(email));
//    }
//}
