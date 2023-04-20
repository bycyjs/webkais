package com.liuhao.webkaishi.service.impl;


import com.liuhao.webkaishi.common.R;
import com.liuhao.webkaishi.entity.Pager;
import com.liuhao.webkaishi.entity.Tokens;
import com.liuhao.webkaishi.entity.User;
import com.liuhao.webkaishi.mapper.TokensMapper;
import com.liuhao.webkaishi.mapper.UserMapper;
import com.liuhao.webkaishi.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    TokensMapper tokensMapper;

    /*登陆*/
    @Override
    public R<Tokens> login(HttpServletRequest request, User user) {

        User login = userMapper.login(user.getUser());
        if (login == null) {
            log.info("账号错误");
            return R.error("账号错误");
        }
        if (!(login.getPassword().equals(user.getPassword()))) {
            log.info("密码错误");
            log.info(login.getPassword() + "=====" + user.getPassword());
            return R.error("密码错误");
        }

        log.info("登陆成功");
        String token1 = request.getHeader("token");
        if (token1 != null){
            return R.success(tokensMapper.selectToken(token1));
        }else {
            /*生成密钥*/
            String token = UUID.randomUUID()+"";
            /*生成时间*/
            Date date=new Date();
            long time = date.getTime();

            /*存入token*/
            Tokens tokens=new Tokens();
            tokens.setUserId(user.getUser());
            tokens.setExpiresAt(time);
            tokens.setToken(token);

            tokensMapper.addToken(tokens);
            return R.success(tokens);
        }







    }

    /*查找所有用户*/
    @Override
    public R<List<User>> selectAll() {
        List<User> users = userMapper.selectAll();
        return R.success(users);
    }

    /*分页查询*/
    @Override
    public Pager<User> findByUserPager(int page, int size) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("page", (page - 1) * size);
        params.put("size", size);
        List<User> list = userMapper.findUserByPage(params);
        Pager<User> pager = new Pager<User>();
        pager.setData(list);
        pager.setTotal(userMapper.findUserCount());
        return pager;
    }

    /*添加用户*/
    @Override
    public R addUser(User user) {
        User users = userMapper.selectName(user);
        if(users!=null){
            return R.error("用户名重复");
        }
        try {
            userMapper.addUser(user);
            return R.success("用户添加成功");
        } catch (Exception e) {
            return R.error("用户添加失败");
        }

    }


/*删除用户*/
    @Override
    public R deleteUserId(Integer id) {


        try {
            userMapper.deleteUserId(id);
            log.info("用户删除成功");
            return R.success("用户删除成功");
        } catch (Exception e) {
            log.info("用户删除失败");
            return R.error("用户删除失败");

        }
    }
/*修改用户*/
    @Override
    public R updateUser(User user) {

        try {
            userMapper.updateUser(user);
            log.info("用户修改成功");
            return R.success("用户修改成功");
        } catch (Exception e) {
            log.info("用户修改失败");
            return R.error("用户修改失败");

        }

    }

    @Override
    public Tokens selectTokens(String token) {
        return tokensMapper.selectToken(token);
    }

    /*验证登陆*/

    @Override
    public R loginValidate(HttpServletRequest request) {
        String token = request.getHeader("token");
        try {
            Tokens tokens = tokensMapper.selectToken(token);
            long expiresAt = tokens.getExpiresAt();
            /*过期时间*/
            long timeExpire= 60*60*24*7;
            Date date=new Date();
            Integer time = Math.toIntExact(date.getTime());
            if((time-timeExpire)<expiresAt){
                return R.success(tokens);
            }else {
                try{
                    tokensMapper.deleteToken(tokens);
                    log.info("删除数据库密钥成功");
                }catch (Exception e){
                    log.info("删除数据库密钥失败");
                }

                return R.error("登陆已经过期");
            }
        }catch (Exception e){
            return R.error("用户未登陆");
        }

    }

    @Override
    public R cancelLogin(HttpServletRequest request) {
        return null;
    }
}
