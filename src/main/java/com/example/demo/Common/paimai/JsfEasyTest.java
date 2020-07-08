package com.example.demo.Common.paimai;

import com.example.demo.Common.HostTools;

/**
 * @author ccjh1
 * @creat 2020/4/10
 */
public class JsfEasyTest {

    public static String easyTestJSF(String interfaceName, String methodName,String alias, String parameterJson,  String token, String ip, String port){
        String url="http://easytest.jd.com/ServiceController/runJsf?interfaceName=\""+interfaceName+
                "\"&methodName=\""+methodName+
                "\"&group=\""+alias+
                "\"&paramValue=["+ parameterJson +
                "]&token=\""+token+
                "\"&ip=\""+ ip+
                "\"&port=\""+ port +
                "\"&encoding=\"UTF-8\"";
        String response="";
        System.out.println("【JsfEasyTest.easyTestJSF】easyTestJSF链接为："+url);
        try {
            response = HostTools.responseText(url,"post",null);
        }catch (Exception e){
            System.out.println("【JsfEasyTest.easyTestJSF】easyTestJSF链接为："+url);
        }
        return response;
    }

    public static void ABC(){
        String interfaceName="com.jd.auction.search.soa.client.service.UnifiedJsfSearchService";
        String methodName="unifiedSearch";
        String alias ="common_yufa_3";
        String paramterJson="{\"apiType\":10,\"page\":1,\"pageSize\":1}";
        System.out.println(easyTestJSF(interfaceName,methodName,alias,paramterJson,"","",""));
    }
    public static void main(String[] args){
        ABC();
    }

}
