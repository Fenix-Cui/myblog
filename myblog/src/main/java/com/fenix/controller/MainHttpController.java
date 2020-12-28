package com.fenix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/http/main")
@Service
public class MainHttpController {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @RequestMapping("/getMainTitle")
    public String getMainTitle(HttpServletRequest request){

        String sql = "select * from blog;";
        List<Map<String, Object>> mapList = jdbcTemplate.queryForList(sql);
        System.out.println(mapList);

        return String.valueOf(mapList);
    }

}
