package com.liuhao.webkaishi.service;


import com.liuhao.webkaishi.common.R;
import com.liuhao.webkaishi.entity.Pager;
import com.liuhao.webkaishi.entity.Tokens;
import com.liuhao.webkaishi.entity.User;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface UserService {
    /*登陆*/
    R<Tokens> login(HttpServletRequest request, User user);
    /*查询所有*/
    R<List<User>> selectAll();
    /*分页查询*/
    Pager<User> findByUserPager(int page, int size);

    R addUser(User user);

    R deleteUserId(Integer id);

    R updateUser(User user);

    Tokens selectTokens(String token);

    R loginValidate(HttpServletRequest request);

    R cancelLogin(HttpServletRequest request);
}
