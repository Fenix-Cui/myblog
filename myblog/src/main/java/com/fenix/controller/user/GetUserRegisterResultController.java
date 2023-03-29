package com.fenix.controller.user;

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

@RestController
@RequestMapping("/http/main")
@Service
@CrossOrigin(origins = "*", maxAge = 3600)
public class GetUserRegisterResultController {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @RequestMapping("/getUserRegisterResult")
    public JSON getUserRegisterResult(HttpServletRequest request,
                                  @RequestParam("username") String username,
                                  @RequestParam("password") String password,
                                  @RequestParam("age") int age,
                                  @RequestParam("sex") String sex,
                                  @RequestParam("phone") String phone,
                                  @RequestParam("email") String email,
                                  @RequestParam("birthday") String birthday
    ){
        JSONObject response_json = new JSONObject();
        String sql = "INSERT INTO `user` (username,`password`,sex,age,phone,email,birthday) VALUE(?,?,?,?,?,?,?);";
        try{
            jdbcTemplate.update(sql, new Object[]{username,password,sex,age,phone,email,birthday});
            response_json.put("message", "success");
            return response_json;
        }catch (Exception e){
            response_json.put("message", "error");
            return response_json;
        }
    }

}
