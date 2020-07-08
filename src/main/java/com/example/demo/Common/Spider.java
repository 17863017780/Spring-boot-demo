package com.example.demo.Common;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * @author ccjh1
 * @creat 2020/4/10
 */
public class Spider {

    public static String spider(String rWen){
            String regex_PaimaiId="\"id\":(\\d+)";
            Pattern pattern_Id = Pattern.compile(regex_PaimaiId);
            String result="";
            try {
                Matcher matcher_Id = pattern_Id.matcher(rWen);
                while (matcher_Id.find()) {
                    String id = matcher_Id.group().replace("\"id\":", "");
                    result += "," + id;
                }
            }catch (PatternSyntaxException e){
                //PatternSyntaxException 表示一个正则表达式模式中的语法错误。
                System.out.println("【Spider】.spider,failed,"+e);
            }
        return result;
    }

    public static void main(String[] args){

        String str ="123134";
//        System.out.println();
        pat(str);

        String reg = "[1-9][0-9]{4,14}";
        System.out.println(str.matches(reg)?"合法qq":"非法qq");
    }




    public static void pat(String str){
        //？通配符匹配文件名中的 0 个或 1 个字符
        // * 通配符匹配零个或多个字符
        // 按指定模式在字符串查找
        String line = "This order was placed for QT3000! OK?";
        String pattern = "(\\D*)(\\d+)(.*)";
        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);
        // 现在创建 matcher 对象
        Matcher m = r.matcher(line);
        if (m.find( )) {
            System.out.println(m.group().toString());
            System.out.println("Found value: " + m.group(0) );
            System.out.println("Found value: " + m.group(1) );
            System.out.println("Found value: " + m.group(2) );
            System.out.println("Found value: " + m.group(3) );
        } else {
            System.out.println("NO MATCH");
        }
    }

}
