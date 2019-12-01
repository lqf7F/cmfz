package com.baizhi.dao;

import com.baizhi.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface AdminDao {
    @Select("Select * from admin where username=#{username}")
    Admin login(String username);

    @Update("update admin set name=#{name},password=#{password} where id=#{id}")
    void update(Admin admin);
}
