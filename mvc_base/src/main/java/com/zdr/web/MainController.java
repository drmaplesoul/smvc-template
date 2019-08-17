package com.zdr.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @RequestMapping("/index")
    @ResponseBody
    public String index2(){
        System.out.println("Hello World!");
        return "hello world!";
    }
}
