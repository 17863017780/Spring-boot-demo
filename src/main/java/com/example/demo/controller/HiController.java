package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ccjh1
 * @creat 2019/10/11
 */
@Controller
public class HiController {
    @RequestMapping("/hi")
    public String Hi(){
        return "layui";
    }
}
