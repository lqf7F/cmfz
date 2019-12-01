package com.baizhi.controller;

import com.baizhi.entity.Article;
import com.baizhi.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("article")
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @RequestMapping("queryByPage")
    public Map<String, Object> findAll(Integer page, Integer rows) {
        return articleService.queryByPage(page, rows);
    }

    @RequestMapping("add")
    public void query(Article article) {
        article.setId(UUID.randomUUID().toString());
        article.setCreateDate(new Date());
        articleService.insert(article);
        System.out.println("article = " + article);
    }

    @RequestMapping("update")
    public void update(Article article) {
        articleService.update(article);
        System.out.println("修改" + article);
    }

    @RequestMapping("del")
    public void del(String id) {
        System.out.println("id = " + id);
    }
}
