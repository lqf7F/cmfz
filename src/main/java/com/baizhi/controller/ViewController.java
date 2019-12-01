package com.baizhi.controller;


import com.baizhi.entity.Album;
import com.baizhi.entity.Article;
import com.baizhi.entity.Banner;
import com.baizhi.service.AlbumService;
import com.baizhi.service.ArticleService;
import com.baizhi.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ViewController {
    @Autowired
    private BannerService bannerService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private AlbumService albumService;

    @RequestMapping("first_page")
    public Map<String, Object> firstPage(String uid, String type, String sub_type) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> map1 = new HashMap<>();
        try {
            List<Banner> banners = bannerService.selectAll();
            //六张心得专辑
            List<Album> albums = albumService.selectAll();
            //当前用户上师发表的两篇新的文章
            List<Article> articles = articleService.selectAll();
            map1.put("albums", albums);
            map1.put("articles", articles);

            map.put("code", 200);
            map.put("header", banners);
            map.put("body", map1);
        } catch (Exception e) {
            map.put("code", 500);
            map.put("msg", "参数错误");
            e.printStackTrace();
        }
        if ("wen".equals(type)) {
            try {
                List<Album> albums = albumService.selectAll();
                map1.put("albums", albums);
                map.put("code", 200);
                map.put("body", map1);
            } catch (Exception e) {
                map.put("code", 500);
                map.put("msg", "参数错误");
                e.printStackTrace();
            }
        }
        if ("si".equals(type)) {
            if ("ssyj".equals(sub_type) || "xmfy".equals(sub_type)) {
                try {
                    List<Article> articles = articleService.selectAll();
                    map.put("code", 200);
                    map.put("body", articles);
                } catch (Exception e) {
                    map.put("code", 500);
                    map.put("msg", "参数错误");
                    e.printStackTrace();
                }
            }
        }
        return map;
    }

}
