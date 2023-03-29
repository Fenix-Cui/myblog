package com.fenix.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("/http/main")
@Service
@CrossOrigin(origins = "*", maxAge = 3600)
public class AddComment {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @RequestMapping("/addcomment")
    public JSON addComment(HttpServletRequest request,
                           @RequestParam("blog_title") String blog_title,
                           @RequestParam("username") String username,
                           @RequestParam("content") String content,
                           @RequestParam("create_time") String create_time,
                           @RequestParam("username_parent") String username_parent
                           ) throws IOException{
        JSONObject response_json = new JSONObject();

        String sql = "INSERT INTO `comment` (blog_title, username, content, create_time, username_parent) VALUES (?,?,?,?,?)";
        try{
            jdbcTemplate.update(sql, new Object[]{blog_title,username,content,create_time,username_parent});

            response_json.put("message", "success");
            return response_json;
        }catch (Exception e){
            response_json.put("message", "false");
            return response_json;
        }
    }
}
