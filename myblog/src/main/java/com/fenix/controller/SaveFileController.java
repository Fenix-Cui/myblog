package com.fenix.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

import static com.fenix.public_class.public_method.save_file;

@RestController
@RequestMapping("/http/main")
@Service
@CrossOrigin(origins = "http://localhost:3333", maxAge = 3600)
@Transactional
public class SaveFileController {
    @RequestMapping("/saveFile")
    @ResponseBody
    public JSON saveFile(HttpServletRequest request, @RequestParam("file") MultipartFile file){
        String content = "";
        content = save_file(file, content);
        JSONObject response_json = new JSONObject();

        if (content.equals("")){
            response_json.put("save_path","");
            System.out.println("文件保存失败");
            return response_json;
        }
        response_json.put("save_path", content);
        return response_json;
    }

}