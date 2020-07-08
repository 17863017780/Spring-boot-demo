package com.example.demo.Common;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ccjh1
 * @creat 2019/10/14
 */
public class MapTest {
    
    public static void main(String[] args){
        Map<String, Object> param1=new HashMap<>();
        param1.put("province","北京市");
        param1.put("apiType",1);
        param1.put("page",1);

        System.out.println(param1.toString());
    }

}
