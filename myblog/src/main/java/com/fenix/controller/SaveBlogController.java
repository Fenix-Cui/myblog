package com.fenix.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import static com.fenix.public_class.public_method.save_file;

@RestController
@RequestMapping("/http/main")
@Service
@CrossOrigin(origins = "*", maxAge = 3600)
public class SaveBlogController {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @RequestMapping("/saveBlog")
    public JSON saveBlog(HttpServletRequest request,
                         @RequestParam("mdTitle") String title,
                         @RequestParam("category") String category,
                         @RequestParam("author") String author,
                         @RequestParam("date_create") String create_time,
                         @RequestParam("date_update") String update_time,
                         @RequestParam("save_path") String save_path
    ) throws IOException {

        // 去除文件名后缀作为标题
        title = title.substring(0,title.lastIndexOf("."));

        ReadMarkDownController readMarkDownController = new ReadMarkDownController();
        String content = readMarkDownController.getMarkDown(save_path);

        JSONObject response_json = new JSONObject();

        // 插入博客数据
        String sql = "INSERT INTO blog(title,author,category,content,create_time,update_time) VALUE(?,?,?,?,?,?);";
        try{
//            注释为测试使用，非必要代码
//            String create_time_date = String.format(create_time, "yyyy-MM-dd HH:mm:ss");
//            String update_time_date = String.format(update_time, "yyyy-MM-dd HH:mm:ss");
//            Timestamp timestamp_create_time = Timestamp.valueOf(create_time_date);
//            Timestamp timestamp_update_time = Timestamp.valueOf(update_time_date);
//            jdbcTemplate.update(sql, new Object[]{title,author,category,content,timestamp_create_time,timestamp_update_time});

            jdbcTemplate.update(sql, new Object[]{title,author,category,content,create_time,update_time});

            response_json.put("message", "success");
            return response_json;
        }catch (Exception e){
            response_json.put("message", "false");
            return response_json;
        }
    }
}
