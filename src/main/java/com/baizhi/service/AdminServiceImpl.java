package com.baizhi.service;

import com.baizhi.dao.AdminDao;
import com.baizhi.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminDao adminDao;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Map<String, Object> login(String username, String password, String enCode, HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        String imgCode = (String) session.getAttribute("imgCode");
        if (enCode.equals(imgCode)) {
            Admin admin = adminDao.login(username);
            session.setAttribute("admin", admin);
            if (admin != null) {
                if (password.equals(admin.getPassword())) {
                    map.put("msg", "ok");
                    map.put("admin", admin);
                    return map;
                } else {
                    map.put("msg", "密码错误");
                    return map;
                }

            } else {
                map.put("msg", "用户名不存在");
                return map;
            }

        } else {
            map.put("msg", "验证码错误");
            return map;
        }
    }
}

