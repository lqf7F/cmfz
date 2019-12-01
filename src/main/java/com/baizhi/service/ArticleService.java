package com.baizhi.service;

import com.baizhi.entity.Article;

import java.util.List;
import java.util.Map;

public interface ArticleService {
    public Map<String, Object> queryByPage(Integer page, Integer rows);

    public void insert(Article article);

    public void update(Article article);

    public List<Article> selectAll();
}
