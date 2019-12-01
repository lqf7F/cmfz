package com.baizhi.controller;


import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import io.goeasy.GoEasy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("date")
    public Map<String, Integer> selectdate() {
        Map date = userService.selectByt();
        return date;
    }

    @RequestMapping("province")
    public Map<String, Object> selectprovince() {
        return userService.selectSex();
    }

    @RequestMapping("insert")
    public void insert() {
        GoEasy goEasy = null;
        String uuid = UUID.randomUUID().toString().replace("-", "");
        try {

            User user = new User(uuid, "王五", "广东省", new Date(), 1);
            userService.insert(user);
            goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-90d8e6e924df45dca08793f840b92664");
            goEasy.publish("asd", "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            goEasy.publish("asd", "添加异常");
        }
    }


}
