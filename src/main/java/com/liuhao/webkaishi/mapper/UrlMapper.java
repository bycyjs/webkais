package com.liuhao.webkaishi.mapper;

import com.liuhao.webkaishi.entity.Url;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface UrlMapper {

    @Select("select  * from imgurl  limit #{page},#{size}")
    List<Url> imgfindUserByPage(Map<String, Object> map);

    @Select("select count(1) from imgurl")
    long imgfindUserCount();

    @Insert("insert into imgurl (url,time,name) value (#{url},#{time},#{name})")
    void imgAddUrl(Url url);
}
