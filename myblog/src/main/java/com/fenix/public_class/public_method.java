package com.fenix.public_class;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class public_method {


    public static String save_file(MultipartFile file, String content){

        String save_path = "D:/WebSite Folder/Fenix_Blog/file/";  // 存储路径
        String save_path_name = file.getOriginalFilename();  // 获取文件名（包括后缀）
        save_path += save_path_name;
        FileOutputStream fos = null;
        try{
            fos = new FileOutputStream(save_path);
            fos.write(file.getBytes());  // 写入文件
            content += save_path;
        } catch (Exception e) {
            System.out.println("文件上传失败");
            content = "";
        } finally {
            try{
                fos.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        return content;
    }


}
