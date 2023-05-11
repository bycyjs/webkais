package com.liuhao.webkaishi.service.impl;

import com.liuhao.webkaishi.common.R;
import com.liuhao.webkaishi.entity.Tokens;
import com.liuhao.webkaishi.mapper.TokensMapper;
import com.liuhao.webkaishi.mapper.UserFileMapper;
import com.liuhao.webkaishi.service.FileService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class FileServiceImpl implements FileService {
    @Autowired
    TokensMapper tokensMapper;
    @Autowired
    UserFileMapper fileMapper;

    @Override

    public R fileupload(HttpServletRequest request, MultipartFile myFile) throws IOException {
        if (myFile == null) {
            log.info("上传文件为空");
            return R.error("上传文件为空");

        }
        /*将上传文件保存*/
        /*1.获得上传文件的输入流*/
        InputStream inputStream = myFile.getInputStream();
        /*2.获得上传文件的位置的输出流*/
        /*格式化时间*/
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        /*设置当前时间格式*/
        simpleDateFormat.applyPattern("yyyy-MM-dd");
        /*获取当前时间*/
        Date date = new Date();
        String time = simpleDateFormat.format(date);

        /*查找用户名*/
        String token = request.getHeader("token");
        Tokens tokens = tokensMapper.selectToken(token);
        com.liuhao.webkaishi.entity.File file1 = new com.liuhao.webkaishi.entity.File();
        file1.setName(tokens.getUserId());
        file1.setTime(time);
        file1.setFilename(myFile.getOriginalFilename());




        File file = new File("F:\\apache-tomca\\nginx-1.23.3\\html\\userUpload\\" + tokens.getUserId() + "\\" + time);
        /*判断目录是否存在*/
        if (!file.exists()) {
            /*创建不存在的目录*/
            boolean dr = file.mkdirs();
        }

        OutputStream outputStream = new FileOutputStream("F:\\apache-tomca\\nginx-1.23.3\\html\\userUpload\\" + tokens.getUserId() + "\\" + time + "\\" + myFile.getOriginalFilename());
        /*3.执行文件拷贝*/
        IOUtils.copy(inputStream, outputStream);
        if (fileMapper.selectShow(file1)==null){
            fileMapper.createShow(file1);
        }
        fileMapper.addRecord(file1);
        log.info("接受上传文件成功");
        inputStream.close();
        outputStream.close();

        return R.success("success");
    }

    @Override
    public R downloadFile(HttpServletRequest request) {
        String token = request.getHeader("token");
        Tokens tokens = tokensMapper.selectToken(token);
        com.liuhao.webkaishi.entity.File file=new com.liuhao.webkaishi.entity.File();
        file.setName(tokens.getUserId());
        String s = fileMapper.selectShow(file);
        if (s==null){
            log.info("你没有上传过文件");
           return R.error("你没有上传过文件");

        }
        List<com.liuhao.webkaishi.entity.File> files = fileMapper.selectFile(file);
        if(files==null){
            return R.error("你没有上传过文件");
        }
        return R.success(files);
    }

    @Override
    public R deleteFile(HttpServletRequest request,com.liuhao.webkaishi.entity.File file) {
        String token = request.getHeader("token");
        Tokens tokens = tokensMapper.selectToken(token);
        file.setName(tokens.getUserId());
        try {
            System.out.println(file);
            fileMapper.deleteFile(file);
            //删除files文件夹下文件名为name.txt的文件
            File folder = new File("F:\\apache-tomca\\nginx-1.23.3\\html\\userUpload\\" + tokens.getUserId() + "\\" + file.getTime());
            File[] files = folder.listFiles();
            for(File filet:files){
                if(filet.getName().equals(file.getFilename())){
                    filet.delete();
                    log.info("删除成功");
                }
            }

            return R.success("删除成功");
        }catch (Exception e){
            log.info(String.valueOf(e));
            return R.error("删除失败");
        }

    }
}
