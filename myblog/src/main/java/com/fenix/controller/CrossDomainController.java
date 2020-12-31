package com.fenix.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(value = "*", maxAge = 3600, allowedHeaders = "*")
public class CrossDomainController {

    @GetMapping("/crossdomain")
    public String result(){
        return "success";
    }
}
