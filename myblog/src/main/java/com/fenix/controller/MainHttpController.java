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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/http/main")
@Service
@CrossOrigin(origins = "*", maxAge = 3600)
public class MainHttpController {


    @Autowired
    private JdbcTemplate jdbcTemplate;
    @RequestMapping("/getMainTitle")
    public List getMainTitle(HttpServletRequest request, @RequestParam("data") String data){

        String sql = "select * from blog where category=?;";
        List<Map<String, Object>> mapList = jdbcTemplate.queryForList(sql, new String[]{data});
        List response_list = new ArrayList();
        for (int i = 0; i < mapList.size(); i++) {
            JSON response_json = (JSON) JSONObject.toJSON(mapList.get(i));  // 选择返回的列表中第i个值,将Map类型转换为json
            response_list.add(response_json);
        }

        return response_list;
    }

}
