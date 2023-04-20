package com.liuhao.webkaishi.config;


import com.liuhao.webkaishi.entity.Tokens;
import com.liuhao.webkaishi.interceptor.AuthInterceptor;
import com.liuhao.webkaishi.mapper.TokensMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 解决跨域问题
 */

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Bean
    public AuthInterceptor setBean2() {
        System.out.println("注入了handler");
        return new AuthInterceptor();
    }


    //配置跨域
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        // 设置允许跨域请求的域名
        config.addAllowedOrigin("*");
        // 是否允许证书
        config.setAllowCredentials(false);
        // 设置允许的方法
        config.addAllowedMethod("*");
        // 允许任何头
        config.addAllowedHeader("*");
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", config);
        return new CorsFilter(configSource);
    }

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(setBean2())
                // 只拦截test路径
                .addPathPatterns("/user/filter/**")
                // 不拦截pass路径
                .excludePathPatterns("/pass/**");
    }


}
