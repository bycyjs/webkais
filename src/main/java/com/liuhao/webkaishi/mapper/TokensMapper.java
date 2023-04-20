package com.liuhao.webkaishi.mapper;


import com.liuhao.webkaishi.entity.Tokens;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface TokensMapper {

    @Insert("insert into tokens (token,userId,expiresAt) value (#{token},#{userId},#{expiresAt})")
    void addToken(Tokens tokens);

    @Select("select * from tokens where token=#{token}")
    Tokens selectToken(String token);

    @Delete("delete from tokens where token=#{token}")
    void deleteToken(Tokens tokens);
}
