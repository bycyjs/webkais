package com.liuhao.webkaishi.interceptor;

import com.liuhao.webkaishi.entity.Tokens;
import com.liuhao.webkaishi.entity.User;
import com.liuhao.webkaishi.mapper.TokensMapper;
import com.liuhao.webkaishi.mapper.UserMapper;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

@Slf4j
@Configuration
public class AuthInterceptor implements HandlerInterceptor {


    @Autowired
    TokensMapper tokensMapper;
    @Autowired
    UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {



        String token = request.getHeader("token");
        System.out.println("token>>>>" + token);
        if (token == null) {
            return false;
        }
        try {
            Tokens tokens = tokensMapper.selectToken(token);
            log.info("查找：》》》》》"+tokens);
            long expiresAt = tokens.getExpiresAt();
            /*过期时间*/
            long timeExpire=1000*60*60*24*7;
            Date date=new Date();
            long time =date.getTime();
            log.info(String.valueOf((time-timeExpire)));
            log.info(String.valueOf(expiresAt));
            if((time-timeExpire)<expiresAt){
                log.info("过滤成功");
                return true;
            }else {
                log.info("过滤失败");
                return false;
            }
        }catch (Exception e){
            log.info(String.valueOf(e));
            log.info("报错");
            return false;
        }



    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
