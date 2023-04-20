package com.liuhao.webkaishi.controller;


import com.liuhao.webkaishi.common.R;
import com.liuhao.webkaishi.entity.Pager;
import com.liuhao.webkaishi.entity.Tokens;
import com.liuhao.webkaishi.entity.User;
import com.liuhao.webkaishi.mapper.TokensMapper;
import com.liuhao.webkaishi.service.UserService;
import com.liuhao.webkaishi.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    UserService userService;


    @PostMapping("/login")
    public R<Tokens> login(HttpServletRequest request, HttpServletResponse response, @RequestBody User user) {
        System.out.println(user);
        return userService.login(request, user);
    }


    @GetMapping("/filter/selectAll")
    public R<List<User>> selectAll() {

        return userService.selectAll();
    }

    @GetMapping("/filter/findByUserPager")
    public Pager<User> findByUserPager(int page, int size) {

        return userService.findByUserPager(page, size);
    }

    @PostMapping("/filter/addUser")
    public R addUser(@RequestBody User user) {
        log.info(String.valueOf(user));
        return userService.addUser(user);
    }

    @DeleteMapping("/filter/deleteUserId/{id}")
    public R deleteUserId(@PathVariable("id") Integer id) {
        log.info(String.valueOf(id));
        return userService.deleteUserId(id);
    }

    @PostMapping("/filter/updateUser")
    public R updateUser(@RequestBody User user) {

        log.info(String.valueOf(user));
        return userService.updateUser(user);
    }

    @Autowired
    TokensMapper tokensMapper;

    @GetMapping("/filter/loginState")
    public R loginState(HttpServletRequest request) {

        String token = request.getHeader("token");
        Tokens tokens = tokensMapper.selectToken(token);
        if (tokens != null) {
            return R.success(tokens);
        }
        return R.error("你未登陆");
    }
}
