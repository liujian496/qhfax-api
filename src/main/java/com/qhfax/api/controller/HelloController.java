package com.qhfax.api.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private static final Logger logger = LogManager.getLogger(HelloController.class);

    @RequestMapping("/hello")
    public String sayHello(){
        logger.info("=== Hello, qhfax-api-2 ===");
        return ("Hello, qhfax-api-2");
    }

    public String mytest() {
        return null;
    }

    public String mytest1() {
        return "mytest1";
    }

    public String mytest2() {
        return "mytest2";
    }
}
