package com.example.demo.Common.paimai;

import ch.qos.logback.core.joran.conditional.ElseAction;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.Common.HostTools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ccjh1
 * @creat 2020/3/27
 */
public class Judge {


    public static void judgePage(String paimaiId){
        try{
                String url1 ="http://beta-api.m.jd.com/api?appid=paimai-search-soa&functionId=paimai_unifiedSearch&body={\"apiType\":10,\"page\":1,\"pageSize\":1,\"paimaiIdList\":\""+paimaiId+"\"}";
                String url2 ="http://beta-api.m.jd.com/api?appid=paimai&functionId=getSearchProducts&body={\"apiType\":10,\"page\":1,\"pageSize\":1,\"paimaiIdList\":\""+paimaiId+"\"}";
                String response=judgeSame(url1,url2,paimaiId);
                if (response.contains("success")){
                    System.out.println(paimaiId+"运行正常"+response);
                }else{
                    System.out.println(response);
                }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static String judgeSame(String url1,String url2,String paimaiId){
        int k=0;
        Map<String,String> params =new HashMap<String,String>();
        params.put("referer","https://mauction.jd.com");
        if (k == 0) {
            String result1 = HostTools.responseText(url1,"get",params);
            String result2 = HostTools.responseText(url2,"get",params);
            if (result1.equals(result2)){
                return "success"+result1;
            }else{
                return "Ht集群"+paimaiId+"=="+result1+"\n==627集群=="+result2;
            }
        }else {
            try {
                String result1 = HostTools.responseText(url1,"get",params);
                String result2 = HostTools.responseText(url2,"get",params);
                if (Patton(result1,result2)){
                    return "success";
                }else{
                    return "Ht集群"+paimaiId+"=="+result1+"\n==627集群=="+result2;
                }
            }catch (Exception e){
                return "[Exception]judgeSame.judgeSame,failed"+e.getMessage();
            }
        }
    }

    public  static boolean Patton(String str1,String str2){
        List<String> strList =new ArrayList<String>();
        strList.add("\"currentPrice\":(\\d+)");
        strList.add("\"auctionStatus\":(\\d+)");
        strList.add("\"displayStatus\":(\\d+)");
        strList.add("\"paimaiTimes\":(\\d+)");
        strList.add("\"publishSource\":(\\d+)");
        strList.add("\"bidCount\":(\\d+)");
        int k=0;
        for (int i = 0; i < strList.size(); i++) {
            String s = (String) strList.get(i);
            String  bidui1=biDui(s,str1);
            String  bidui2=biDui(s,str2);
            if (bidui1.equals(bidui2)){
//                System.out.println("比对数据"+i+bidui1+bidui2);
                k++;
            }
//            else{
//                System.out.println("有一处不同");
//            }
        }
        if (k==6){
            return true;
        }else return false;
    }
    public  static String biDui(String str1,String str2){
        Pattern pattern = Pattern.compile(str1);
        String result="";
        try {
            Matcher matcher_Id = pattern.matcher(str2);
            while (matcher_Id.find()) {
                result =matcher_Id.group();
                break;
            }
        } catch (Exception e) {
            System.out.println("#PaimaiSearch,failed: " + e.getMessage());
        }
        return result;
    }



    //读取本地文件
    public static String txt2String(File file){
        StringBuilder result = new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){   //使用readLine方法，一次读一行
                result.append(System.lineSeparator()+s);
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return result.toString();
    }

    public static void main(String[] args){
        File file =new File("D:/chenjiahong11/Desktop/paimaiID.txt");
        List<String> lists =new ArrayList<String>();
        int i =0;
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){   //使用readLine方法，一次读一行
                i++;
                judgePage(s);
                if (i==100){
                    System.out.println("现在是第"+i+"条");
                    Thread.sleep(1000);
                    i=0;
                }
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }

    }

}
