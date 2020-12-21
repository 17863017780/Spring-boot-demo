//package com.example.demo.Common.utils;
//
//import org.apache.commons.lang.time.DateFormatUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.commons.lang3.time.DateFormatUtils;
//import org.junit.platform.commons.util.StringUtils;
//
//import java.text.DateFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//public class DateFormatUtil {
//    public static final String FORMAT_SIMPLE_DATE = "yyyyMMdd";
//    public static final String FORMAT_NORMAL_DATE = "yyyy-MM-dd";
//    public static final String FORMAT_SIMPLE_TIME = "HHmmss";
//    public static final String FORMAT_NORMAL_TIME = "HH:mm:ss";
//    public static final String FORMAT_SIMPLE_DATE_TIME = "yyyyMMddHHmmss";
//    public static final String FORMAT_NORMAL_DATE_TIME = "yyyy-MM-dd HH:mm:ss";
//    public static final String FORMAT_SIMPLE_DATE_TIME_DETAIL = "yyyyMMddHHmmssSSS";
//    public static final String FORMAT_NORMAL_DATE_TIME_DETAIL = "yyyy-MM-dd HH:mm:ss.SSS";
//
//    public DateFormatUtil() {
//    }
//
//    public static String formatSimpleDate(Date date) {
//        return null == date ? null : DateFormatUtils.format(date, "yyyyMMdd");
//    }
//
//    public static Date parseSimpleDate(String source) throws ParseException {
//        return StringUtils.isBlank(source) ? null : (new SimpleDateFormat("yyyyMMdd")).parse(source);
//    }
//
//    public static String formatNormalDate(Date date) {
//        return null == date ? null : DateFormatUtils.format(date, "yyyy-MM-dd");
//    }
//
//    public static Date parseNormalDate(String source) throws ParseException {
//        return StringUtils.isBlank(source) ? null : (new SimpleDateFormat("yyyy-MM-dd")).parse(source);
//    }
//
//    public static String formatSimpleTime(Date date) {
//        return null == date ? null : DateFormatUtils.format(date, "HHmmss");
//    }
//
//    public static String formatNormalTime(Date date) {
//        return null == date ? null : DateFormatUtils.format(date, "HH:mm:ss");
//    }
//
//    public static String formatSimpleDateTime(Date date) {
//        return null == date ? null : DateFormatUtils.format(date, "yyyyMMddHHmmss");
//    }
//
//    public static String formatNormalDateTime(Date date) {
//        return null == date ? null : DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss");
//    }
//
//    public static Date parseNormalDateTime(String source) throws ParseException {
//        return StringUtils.isBlank(source) ? null : (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(source);
//    }
//
//    public static String formatSimpleDateTimeDetail(Date date) {
//        return null == date ? null : DateFormatUtils.format(date, "yyyyMMddHHmmssSSS");
//    }
//
//    public static String formatNormalDateTimeDetail(Date date) {
//        return null == date ? null : DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss.SSS");
//    }
//
//    public static DateFormat getDateFormat(String pattern) {
//        return new SimpleDateFormat(pattern);
//    }
//
//    public static String format(Date date, String pattern) {
//        return getDateFormat(pattern).format(date);
//    }
//
//    public static Date parse(String source, String pattern) throws ParseException {
//        return getDateFormat(pattern).parse(source);
//    }
//
//    public static Date parseSimpleDateTime(String source) throws ParseException {
//        return StringUtils.isBlank(source) ? null : (new SimpleDateFormat("yyyyMMddHHmmss")).parse(source);
//    }
//}
