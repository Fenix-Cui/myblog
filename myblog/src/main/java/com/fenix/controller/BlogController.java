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
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/http/main")
@Service
@CrossOrigin(origins = "*", maxAge = 3600)
public class BlogController {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @RequestMapping("/getBlog")
    public JSON getBlog(HttpServletRequest request, @RequestParam("id") String id){
        String sql = "SELECT * FROM blog WHERE id=?";
        List<Map<String, Object>> mapList = jdbcTemplate.queryForList(sql, new String[]{id});
        Map<String, Object> map = mapList.get(0);  // 选择返回的列表中第一个值
        JSON response_json = (JSON) JSONObject.toJSON(map);  // 将Map类型转换为json

        return response_json;
    }

}
