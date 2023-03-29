package com.fenix.controller.user;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/http/main")
@Service
@CrossOrigin(origins = "*", maxAge = 3600)
public class GetUserInformationController {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @RequestMapping("/getUserInformation")
    public JSON getUserInformation(HttpServletRequest request, @RequestParam("username") String username){

        String sql = "select * from user where username=?;";
        List<Map<String, Object>> mapList = jdbcTemplate.queryForList(sql, new String[]{username});
        JSON response_json = null;
//        List response_list = new ArrayList();
        for (int i = 0; i < mapList.size(); i++) {
            response_json = (JSON) JSONObject.toJSON(mapList.get(i));  // 选择返回的列表中第i个值,将Map类型转换为json
//            response_list.add(response_json);
        }

        return response_json;
    }

    public int getNum(){
        String s = new String();
        s.intern();
        HashMap<Integer, Integer> hashMap = new HashMap<>();

        hashMap.put(1, 1);
        return 1;
    }
    public int getNum(int a){

        return 1;
    }
}
