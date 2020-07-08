package com.example.demo.controller;

import com.example.demo.Common.QRCodeTest.QRproduct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ccjh1
 * @creat 2019/10/22
 */

@Controller
public class QRcodeController {

//    @RequestMapping("/QRcode")
//    @ResponseBody

    private String theSetDir; //全局配置文件中设置的图片的路径
    public static String QRcode(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("text/html; charset=utf-8");
        String content =request.getParameter("type");
        String filename =QRproduct.CreateQRProduct(content);
        System.out.println("QRcode------Success------filename:"+filename);
        return filename;
    }

    @RequestMapping("/toQRcode")
    public static String QRcodeindex(){
        return "QRcode";
    }


}
