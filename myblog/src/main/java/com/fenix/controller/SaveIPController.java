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
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/http/main")
@Service
@CrossOrigin(origins = "*", maxAge = 3600)
public class SaveIPController {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @RequestMapping("/saveIP")
    public JSON saveIP(HttpServletRequest request,
                       @RequestParam("ip") String ip,
                       @RequestParam("city") String city){
        String sql = "INSERT INTO `user_ip` (username, user_ip, user_city, visit_time) VALUES ('test', ?, ?, ?);";

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String visit_time = df.format(new Date());// new Date()为获取当前系统时间

        JSONObject response_json = new JSONObject();
        try{
            jdbcTemplate.update(sql, new Object[]{ip, city, visit_time});
            response_json.put("message", "success");
            return response_json;
        }catch (Exception e){
            response_json.put("message", "false");
            return response_json;
        }
    }
}
