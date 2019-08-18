package com.zdr.service;


import com.zdr.annotion.CatTranstion;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MylogService {
    private final static Logger logger = LoggerFactory.getLogger(MylogService.class);

    @CatTranstion(name="Cat-log")
    public String log(String str){
        logger.info("{}",str);
        log.info("lombok log {},",str);
        return str;
    }
}
