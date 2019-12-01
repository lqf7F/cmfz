package com.baizhi.controller;

import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@RequestMapping("/exiti")
public class ExitController {
    @RequestMapping("/exit")
    public String exit(HttpSession session) {
        session.removeAttribute("admin");
        return null;
    }
}
