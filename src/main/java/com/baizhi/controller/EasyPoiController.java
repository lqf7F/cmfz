package com.baizhi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("easypoi")
@RestController
public class EasyPoiController {
    @RequestMapping("out")
    public String test() {

        return null;
    }
}
