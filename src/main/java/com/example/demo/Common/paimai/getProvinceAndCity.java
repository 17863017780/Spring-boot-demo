package com.example.demo.Common.paimai;

import com.example.demo.Common.HostTools;

import java.sql.Time;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author ccjh1
 * @creat 2020/1/10
 */
public class getProvinceAndCity {


    
    public static String getProvinceAndCityTest(String lng,String lat){
//        String webUrlApi="https://api.m.jd.com/api?appid=paimai&functionId=getProvinceAndCity&body={\"lng\":\""+lng+"\",\"lat\":\""+lat+"\"}";
//        String webUrlBeta_Api="https://beta-api.m.jd.com/api?appid=paimai&functionId=getProvinceAndCity&body={\"lng\":\""+lng+"\",\"lat\":\""+lat+"\"}";
        String webUrlApi="https://api.m.jd.com/api?appid=auction-mini-program&functionId=getAreaInfoByLatlng&body={\"lng\":\""+lng+"\",\"lat\":\""+lat+"\"}";
        String webUrlBeta_Api="https://beta-api.m.jd.com/api?appid=auction-mini-program&functionId=getAreaInfoByLatlng&body={\"lng\":\""+lng+"\",\"lat\":\""+lat+"\"}";
       try {
            String resultApi = HostTools.responseText(webUrlApi,"get",null);
            String resultBeta_Api = HostTools.responseText(webUrlBeta_Api,"get",null);
            if (resultApi.equals(resultBeta_Api)){
                return "success，response=="+resultApi;
            }else{
//                System.out.println("resultApi=="+resultApi);
//                System.out.println("resultBeta_Api=="+resultBeta_Api);
                return "failed,lng=="+lng+",lat=="+lat;

            }
        }catch (Exception e){
            return "[Exception]getProvinceAndCity.getProvinceAndCityTest,failed"+e.getMessage();
        }
    }
    public static void hashMapT(){
        List<String> listString= new ArrayList<>();
//        listString.add("台湾省台中市南区,24.121689,120.657349");
        listString.add("北京市东城区崇文门东大街,39.901309,116.424866");
//        listString.add("北京市西城区长椿街45号,39.892145,116.362340");
//        listString.add("上海市黄浦区南京东路586号,31.235114,121.475830");
//        listString.add("天津市津南区,38.947661,117.368317");
//        listString.add("重庆市大渡口区金桥路,29.449165,106.478119");
//        listString.add("香港特别行政区元朗区寿富街29号,22.442764,114.032593");
//        listString.add("广东省深圳市光明区五号路9号,22.743256,113.936462");
//        listString.add("内蒙古自治区阿拉善盟阿拉善左旗安德街,38.852542,105.721436");
//        listString.add("山西省太原市万柏林区千峰园北街,37.880273,112.531586");
//        listString.add("陕西省西安市莲湖区新园路2号,34.268566,108.906097");
//        listString.add("黑龙江省哈尔滨市阿城区上京大道,45.548679,126.955261");
//        listString.add("浙江省杭州市上城区笤帚湾,30.218729,120.169830");
//        listString.add("海南省陵水黎族自治县陵水黎族自治县,18.570758,109.855042");
//        listString.add("山东省青岛市崂山区银川东路12号,36.109034,120.473328");
//        listString.add("新疆维吾尔自治区五家渠市五家渠市人民南路,44.162504,87.545929");
//        listString.add("福建省莆田市荔城区文献东路,25.428393,119.031372");
//        listString.add("澳门特别行政区凼仔柯维纳马路212号,22.154339,113.556061");
//        listString.add("湖南省湘潭市雨湖区吉利东路,27.936181,112.961426");
//        listString.add("江苏省南京市江宁区建衡路,31.952162,118.916016");

//        List<String> listString2= new ArrayList<>();
//        listString2.add("北京市东城区崇文门东大街,39.901309,116.424866");
//        listString2.add("上海市黄浦区南京东路586号,31.235114,121.475830");
//        listString2.add("黑龙江省哈尔滨市阿城区上京大道,45.548679,126.955261");
//        listString2.add("山东省青岛市崂山区银川东路12号,36.109034,120.473328");
        int Sa=0;int Sb=0;int i;
        for (i=0;i<50;i++){
            Iterator it =listString.iterator();
            while (it.hasNext()){
                String[] lngAndLat=((String)it.next()).split(",");
                String a =getProvinceAndCityTest(lngAndLat[2],lngAndLat[1]);
                if (a.contains("success")){
                    Sa++;
                    System.out.println(a);
                }else if (a.contains("failed")){
                    Sb++;
                }else System.out.println("城市:"+lngAndLat[0]+",getProvinceAndCityTest==="+getProvinceAndCityTest(lngAndLat[2],lngAndLat[1]));
            }
            /**
             * 加入等待
             */
//            try {
//                TimeUnit.SECONDS.sleep(30);
//
//            } catch (InterruptedException e) {
//                System.out.println("[InterruptedException]==等待时间"+e.getMessage());
//            }
        }
        System.out.println("总数==="+i+",成功数==="+Sa+",失败数==="+Sb);
    }

    public static void main(String[] args){
        hashMapT();
    }
}
