package com.baizhi.controller;

import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TwoController {
    @Autowired
    private ChapterService chapterService;

    @RequestMapping("detail")
    public Map<String, Object> detail(String id, String uidc) {
        Map<String, Object> map = new HashMap<>();
        Chapter chapter = chapterService.selectById(id);
        map.put("chapter", chapter);
        return map;
    }
}
