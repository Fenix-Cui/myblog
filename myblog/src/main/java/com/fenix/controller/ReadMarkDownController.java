package com.fenix.controller;

import org.pegdown.Extensions;
import org.pegdown.PegDownProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//@RestController
//@RequestMapping("/http/main")
//@Service
//@CrossOrigin(origins = "*", maxAge = 3600)
public class ReadMarkDownController {

//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//    @RequestMapping("/getMarkDown")
//    public String getMarkDown(@RequestParam("fil"+"e") MultipartFile mdFile) throws IOException {
    public String getMarkDown(String blogContentPath) throws IOException {
        String markdown_path = blogContentPath;
        File file = new File(markdown_path);
        FileInputStream fileInputStream = null;
        fileInputStream = new FileInputStream(markdown_path);
        MultipartFile multipartFile = new MockMultipartFile(file.getName(), fileInputStream);

        String markdown_text = null;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(multipartFile.getInputStream(), "UTF-8"));

        int size = (int)multipartFile.getSize();
        char[] cbconf = new char[size];
        while(bufferedReader.read(cbconf)!=-1){
            markdown_text = new String(cbconf);
        }

        PegDownProcessor pdp = new PegDownProcessor(Integer.MAX_VALUE);
        String htmlContent = pdp.markdownToHtml(markdown_text);;

        htmlContent = htmlContent.replace("\u0000", ""); // removes NUL chars
//        string = string.replace("\\u0000", ""); // removes backslash+u0000

        return htmlContent;
    }
}
