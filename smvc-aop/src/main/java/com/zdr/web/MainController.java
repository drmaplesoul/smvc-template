package com.zdr.web;

import com.zdr.service.MylogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
public class MainController {
    @Autowired
    MylogService service;

    @RequestMapping("/index")
    @ResponseBody
    public String index2(){
        String logstr = service.log("Hello World!");
        log.info("service.log 返回值：{}",logstr);
        return "hello world!";
    }
}
