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
public class RegisterResultController {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @RequestMapping("/getRegisterResult")
    public JSON getRegisterResult(HttpServletRequest request,
                                  @RequestParam("username") String username,
                                  @RequestParam("email") String email,
                                  @RequestParam("password") String password,
                                  @RequestParam("rePassword") String rePassword,
                                  @RequestParam("phone") String phone,
                                  @RequestParam("code") String code
    ){
        JSONObject response_json = new JSONObject();
        String sql = "INSERT INTO user(username,email,password,phone) VALUE(?,?,?,?);";
        try{
            jdbcTemplate.update(sql, new Object[]{username,email,password,phone});
            response_json.put("message", "success");
            return response_json;
        }catch (Exception e){
            response_json.put("message", "error");
            return response_json;
        }
    }
}
