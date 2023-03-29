package com.fenix.controller.user;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fenix.annotation.RequestLimit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
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
@Controller  // 添加Controller注解
public class GetMainTitleController {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @RequestLimit(count = 1, time=60000)  // 拦截器次数与实践
    @RequestMapping("/getMainTitle")
    public List getMainTitle(HttpServletRequest request, @RequestParam("data") String data){
        String sql = "";
        String undefined = "$undefined$";
        List<Map<String, Object>> mapList;
        if(data.equals(undefined)){
            sql = "SELECT * FROM `blog`;";
            mapList = jdbcTemplate.queryForList(sql);
        }else{
            sql = "SELECT * FROM `blog` WHERE category=?;";
            mapList = jdbcTemplate.queryForList(sql, new String[]{data});
        }

        List response_list = new ArrayList();
        for (int i = 0; i < mapList.size(); i++) {
            JSON response_json = (JSON) JSONObject.toJSON(mapList.get(i));  // 选择返回的列表中第i个值,将Map类型转换为json
            response_list.add(response_json);
        }

        return response_list;
    }

}
