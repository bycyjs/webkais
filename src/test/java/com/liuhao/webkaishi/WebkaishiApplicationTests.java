package com.liuhao.webkaishi;

import com.liuhao.webkaishi.entity.*;
import com.liuhao.webkaishi.mapper.TokensMapper;
import com.liuhao.webkaishi.mapper.UrlMapper;
import com.liuhao.webkaishi.mapper.UserFileMapper;
import com.liuhao.webkaishi.mapper.UserMapper;
import com.liuhao.webkaishi.service.impl.UserServiceImpl;
import org.assertj.core.util.DateUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.rmi.server.UID;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@SpringBootTest
class WebkaishiApplicationTests {

    @Autowired
    UserServiceImpl userService;
    @Test
    void contextLoads() {
    }
    @Test
    void fy(){
        Pager<User> byUserPager = userService.findByUserPager(1, 1);
        System.out.println(byUserPager);

    }
    @Autowired
    UrlMapper urlMapper;
    @Test
    void ad(){
        Url [] url= new Url[36];
        for (int i=0;i<url.length;i++);
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        System.out.println(formatter.format(date));




    }
    @Autowired
    TokensMapper tokensMapper;
    @Test
    void token(){
        Tokens tokens = tokensMapper.selectToken("43dfaecf-c3e6-4cd0-a7fa-8a968960a4fd");
        System.out.println(tokens);
    }

    @Test
    void date(){
        Date date=new Date();
        long time = date.getTime();
        System.out.println(time);

    }
    @Autowired
    UserFileMapper userFileMapper;
    @Test
    void selectShow(){
        File file=new File();
        file.setName("test1111");
        userFileMapper.createShow(file);

    }

}
