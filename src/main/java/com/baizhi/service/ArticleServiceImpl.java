package com.baizhi.service;

import com.baizhi.dao.ArticleDao;
import com.baizhi.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleDao articleDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String, Object> queryByPage(Integer page, Integer rows) {
        Map<String, Object> map = new HashMap<>();
        Integer start = (page - 1) * rows;
        List<Article> articles = articleDao.queryBypage(start, rows);
        Integer count = articleDao.getCount();
        Integer total = count % rows == 0 ? count / rows : count / rows + 1;
        map.put("rows", articles);
        map.put("total", total);
        map.put("page", page);
        map.put("records", count);

        return map;
    }

    @Override
    public void insert(Article article) {
        articleDao.insert(article);
    }

    @Override
    public void update(Article article) {
        articleDao.update(article);
    }

    @Override
    public List<Article> selectAll() {
        List<Article> articles = articleDao.selectAll();
        return articles;
    }
}
