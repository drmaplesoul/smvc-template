package com.zdr.web;

import com.zdr.service.MylogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
    @Autowired
    MylogService service;

    @RequestMapping("/index")
    @ResponseBody
    public String index2(){
        service.log("Hello World!");
        return "hello world!";
    }
}
