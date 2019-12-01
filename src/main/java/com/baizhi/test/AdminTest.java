
package com.baizhi.test;


import com.baizhi.dao.*;
import com.baizhi.entity.Album;
import com.baizhi.entity.Article;
import com.baizhi.entity.Banner;
import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;


public class AdminTest extends BasicTest {
    @Autowired
    UserDao userDao;
    @Autowired
    AdminDao adminDao;
    @Autowired
    AlbumDao albumDao;
    @Autowired
    ChapterDao chapterDao;
    @Autowired
    ArticleDao articleDao;
    @Autowired
    BannerDao bannerDao;
    @Autowired
    ChapterService chapterService;

    @Test
    public void test1() {
        List<Album> albums = albumDao.queryByPage(1, 1);
        for (Album album : albums) {
            System.out.println("album = " + album);
        }
    }

    @Test
    public void test2() {
        List<Article> articles = articleDao.queryBypage(1, 1);
        for (Article article : articles) {
            System.out.println("article = " + article);
        }
    }

    @Test
    public void test3() {
        Integer count = articleDao.getCount();
        System.out.println("count = " + count);
    }

    @Test
    public void test4() {
        Date date = new Date();
        Article article = new Article("1", "1", "1", "1", "罗启峰", null);
        articleDao.update(article);
    }

    @Test
    public void test5() {
        Integer integer = userDao.selectByt(7);
        System.out.println(integer);
    }

    @Test
    public void test6() {
        List<Banner> banners = bannerDao.selectAll();
        for (Banner banner : banners) {
            System.out.println("banner = " + banner);
        }
    }

    @Test
    public void test7() {
        List<Album> albums = albumDao.selectAll();
        for (Album album : albums) {
            System.out.println("album = " + album);
        }
    }

    @Test
    public void test8() {
        List<Chapter> chapters = chapterDao.selectAll();
        for (Chapter chapter : chapters) {
            System.out.println("chapter = " + chapter);
        }
    }

    @Test
    public void test9() {
        Chapter chapter = chapterDao.selectById("1292513b-f9ad-4588-9718-b587c2001549");
        System.out.println("chapter = " + chapter);
    }

    @Test
    public void test10() {
        Chapter chapter = chapterService.selectById("1292513b-f9ad-4588-9718-b587c2001549");
        System.out.println("chapter = " + chapter);
    }

    @Test
    public void test11() {
    /*  Admin admin = new Admin("3", "lsg", "123");
      adminDao.update(admin);*/
    }
}

