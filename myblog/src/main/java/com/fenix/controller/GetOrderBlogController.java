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

import java.io.IOException;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/http/main")
@Service
@CrossOrigin(origins = "*", maxAge = 3600)
public class GetOrderBlogController {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @RequestMapping("/getOrderBlog")
    public JSON getOrderBlog(@RequestParam("title") String title) throws IOException {

        String sql = "SELECT * FROM `blog` WHERE title=?";
        JSONObject response_json = null;
        try{
            List<Map<String, Object>> mapList = jdbcTemplate.queryForList(sql, new String[]{title});
            Map<String, Object> map = mapList.get(0);  // 选择返回的列表中第一个值
            response_json = (JSONObject) JSONObject.toJSON(map);  // 将Map类型转换为json
            response_json.put("message", "success");
            return response_json;
        }catch (Exception e){
            response_json.put("message", "success");
            return response_json;
        }

    }
}
