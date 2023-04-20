package com.liuhao.webkaishi.mapper;

import com.liuhao.webkaishi.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper  {

    @Select("select * from user where user=#{user}")
    User login(String user);
    @Select("select * from user")
    List<User> selectAll();

    @Select("select  * from user  limit #{page},#{size}")
    List<User> findUserByPage(Map<String, Object> map);
    @Select("select count(1) from user")
    long findUserCount();
    @Insert(" insert into user (user,password,mailbox,phone) value (#{user},#{password},#{mailbox},#{phone})")
    void addUser(User user);

    @Delete("delete  from user where id=#{id}")
    void deleteUserId(Integer id);

    @Select("select * from user where user=#{user}")
    User selectName(User user);

    @Update("update user set user=#{user},password=#{password},mailbox=#{mailbox},phone=#{phone} where id=#{id}")
    void updateUser(User user);
}
