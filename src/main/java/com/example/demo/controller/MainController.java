package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ccjh1
 * @creat 2019/10/21
 */

@Controller
public class MainController {

    @RequestMapping("/test")
    public  String indexController(){
        return "H123456";
    }
}
