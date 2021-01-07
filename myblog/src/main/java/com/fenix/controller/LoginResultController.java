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
@CrossOrigin(origins = "http://localhost:3333", maxAge = 3600)
public class LoginResultController {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @RequestMapping("/getLoginResult")
    public JSON getLoginResult(HttpServletRequest request,
                               @RequestParam("username") String username,
                               @RequestParam("password") String password
    ){
        String sql = "SELECT * FROM `user` WHERE username=? AND password=?";
        try{
            List<Map<String, Object>> mapList = jdbcTemplate.queryForList(sql, new String[]{username, password});
            Map<String, Object> map = mapList.get(0);  // 选择返回的列表中第一个值
            map.put("message", "success");
            JSON response_json = (JSON) JSONObject.toJSON(map);  // 将Map类型转换为json
            return response_json;
        }catch (Exception e){
            JSONObject response_json = new JSONObject();
            response_json.put("message", "error");
            return response_json;
        }
    }
}
