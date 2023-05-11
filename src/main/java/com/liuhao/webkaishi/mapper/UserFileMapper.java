package com.liuhao.webkaishi.mapper;

import com.liuhao.webkaishi.entity.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserFileMapper {

    @Select("show tables like #{name} ")
    String selectShow(File file);



    void createShow(File file);

    @Insert(" insert into ${name} (time,filename) value (#{time},#{filename})")
    void addRecord(File file);

    @Select("select * from ${name}")
    List<File> selectFile(File file);

    @Delete("delete from ${name} where filename=#{filename} and time=#{time}")
    void deleteFile(File file);


}
