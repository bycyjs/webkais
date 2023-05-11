package com.liuhao.webkaishi.controller;

import com.liuhao.webkaishi.common.R;
import com.liuhao.webkaishi.entity.Tokens;
import com.liuhao.webkaishi.service.impl.FileServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;


@Slf4j
@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    FileServiceImpl fileService;

    @PostMapping("/upload")
    public R fileupload(HttpServletRequest request,@RequestBody MultipartFile myFile) throws IOException {


        return fileService.fileupload(request,myFile);
    }
    @GetMapping("/download")
    public R downloadFile(HttpServletRequest request){
        return fileService.downloadFile(request);
    }
    @PostMapping("/deleteFile")
    public R deleteFile(HttpServletRequest request,@RequestBody  com.liuhao.webkaishi.entity.File file){
        log.info("进入deleteFile");
        return fileService.deleteFile(request,file);
    }
}
