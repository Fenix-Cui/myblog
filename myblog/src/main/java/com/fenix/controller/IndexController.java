package com.fenix.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/index")
    private String index() {
        return "index";
    }

    @RequestMapping("/love02")
    private String love02() {
        return "love02";
    }

    @RequestMapping("/test")
    private String test() {
        return "test";
    }

}
