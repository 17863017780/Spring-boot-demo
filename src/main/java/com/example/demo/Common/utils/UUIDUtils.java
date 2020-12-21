package com.example.demo.Common.utils;

import java.util.UUID;

/**
 * UUID工具类
 *
 * @创建人 sunzhihao
 * @创建时间 2020/3/4 10:24
 * @描述
 */
public class UUIDUtils {

    public static String getUUID() {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        return uuid;
    }
}
