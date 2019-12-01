package com.baizhi.dao;

import com.baizhi.entity.Use;
import com.baizhi.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    public Integer selectByt(@Param("t") Integer t);

    public List<Use> selectSex(@Param("i") Integer i);

    public void insert(User user);

}
