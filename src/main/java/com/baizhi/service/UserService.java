package com.baizhi.service;

import com.baizhi.entity.User;

import java.util.Map;

public interface UserService {
    public Map<String, Integer> selectByt();

    public Map<String, Object> selectSex();

    public void insert(User user);
}
