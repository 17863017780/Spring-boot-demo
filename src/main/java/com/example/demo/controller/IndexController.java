package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author ccjh1
 * @creat 2019/10/11
 */
@RestController
public class IndexController {

    @RequestMapping("/hello")
    public String Hello(){
        return "Hello Spring-boot";
    }


    @RequestMapping("/abc")
    public ModelAndView test(){
        ModelAndView modelAndView=new ModelAndView("/freeMarkerTest");
        modelAndView.addObject("name","老铁");
        modelAndView.addObject("name1","老铁123142342134");
        modelAndView.addObject("name2","老铁12341234213432141234213414234");

        return modelAndView;
    }

}
