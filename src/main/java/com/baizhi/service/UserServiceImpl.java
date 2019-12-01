package com.baizhi.service;

import com.baizhi.dao.UserDao;
import com.baizhi.entity.Use;
import com.baizhi.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String, Integer> selectByt() {
        Integer day7 = userDao.selectByt(7);
        Integer day14 = userDao.selectByt(14);
        Integer day21 = userDao.selectByt(21);
        Map<String, Integer> map = new HashMap<>();
        map.put("day7", day7);
        map.put("day14", day14);
        map.put("day21", day21);
        return map;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Map<String, Object> selectSex() {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Use> man = userDao.selectSex(1);
        List<Use> woman = userDao.selectSex(2);
        for (Use use : woman) {
            System.out.println("womanuse = " + use);
        }
        for (Use use : man) {
            System.out.println("manuse = " + use);
        }
        map.put("man", man);
        map.put("woman", woman);
        return map;
    }

    @Override
    public void insert(User user) {
        userDao.insert(user);
    }
}
