package com.fenix.controller.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/http/main")
@Service
@CrossOrigin(origins = "*", maxAge = 3600)
public class GetMonthVisitsController {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @RequestMapping("/getMonthVisits")
    public List getMonthVisits(HttpServletRequest request){

        String sql = "select * from user_ip where visit_time>=? and visit_time<=?;";

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String visit_end_time= df.format(new Date());// new Date()为获取当前系统时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, - 30);
        Date time = c.getTime();
        String visit_begin_time = sdf.format(time);

        List<Map<String, Object>> mapList = jdbcTemplate.queryForList(sql, new String[]{visit_begin_time, visit_end_time});
        JSON response_json = null;
        List response_list = new ArrayList();
        for (int i = 0; i < mapList.size(); i++) {
            response_json = (JSON) JSONObject.toJSON(mapList.get(i));  // 选择返回的列表中第i个值,将Map类型转换为json
            response_list.add(response_json);
        }

//        JSONObject response_json02 = new JSONObject();
//        int day_vists = response_list.size();
//        response_json02.put("value", day_vists);
//        List response_list02 = new ArrayList();
//        response_list02.add(response_json02);

        return response_list;
    }

}
