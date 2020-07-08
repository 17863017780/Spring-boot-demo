package com.example.demo.Common.uiSelenium;

import org.openqa.selenium.By;

import java.util.Arrays;
import java.util.List;

/**
 * @author ccjh1
 * @creat 2020/1/11
 */
public class LocationUtil {
    public enum ByType {ID,NAME, XPATH,CSS,TAG,CLASS,LINKTEXT,PARTIALLINKTEXT;}

    /**
     * 定位信息，比如id=kw
     * @param var 格式xpath=//*[@id="kw"]
     * @return  org.openqa.selenium.By
     */
    public static By getLocation(String var) {
        List list = Arrays.asList(var.split("=", 2));
        if (list.size() < 2 || "".equals(list.get(1))) {
            return null;
        }
        String loc = String.valueOf(list.get(1));
        try {
            switch (ByType.valueOf(list.get(0).toString().toUpperCase())) {
                case ID:
                    return By.id(loc);
                case NAME:
                    return By.name(loc);
                case XPATH:
//                    System.out.println(var);
                    return By.xpath(loc);
                case CSS:
                    return By.cssSelector(loc);
                case TAG:
                    return By.tagName(loc);
                case CLASS:
                    return By.className(loc);
                case LINKTEXT:
                    return By.linkText(loc);
                case PARTIALLINKTEXT:
                    return By.partialLinkText(loc);
                default:
                    System.out.println("\123413241324132421342314");
                    return null;
            }
        }catch (Exception e){
            System.out.println("[Exception]==LocationUtil.getLocation,failed to locate element: "+var);
            return null;
        }
//        return null;
    }


}
