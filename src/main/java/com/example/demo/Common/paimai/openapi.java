package com.example.demo.Common.paimai;

import com.example.demo.Common.HostTools;
import com.example.demo.Common.HttpClientHostTools;
import com.jd.shorturl.model.UrlInfo;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ccjh1
 * @creat 2020/3/9
 */
public class openapi {

//    public String invoke(){
//        String url="http://smarttest.jd.com/open/api/jsf/invokeHttp";
//        String requests=null;
//        Map<String,String> params =new HashMap<>();
//        params.put("Headers","ccjh1,385ca9d");
//        params.put("body",)
//        requests = HostTools.responseText(url,"post",params);
//
//        return requests;
//    }
    public static void main(String[] args) throws Exception {
        String str = HttpClientHostTools.sendGetData("http://flower.man.jd.com/medal?name=jd_5c01c795994d9",null);
        System.out.println(str);
    }


    /**
     * 长链接转短链接
     */
    //
//    public String getShortUrl(String longUrl) throws Exception {
//        try {
//            UrlInfo shortUrl = shortUrlService.generateURL(longUrl, secretKey);
//            if (shortUrl != null && STATUS.equals(shortUrl.getCode())) {
//                return shortUrl.getShortUrl();
//            }
//        } catch (Exception e) {
//            throw new Exception("长链接转短链接异常：", e);
//        }
//        return null;
//    }

}
