package com.baizhi.dao;


import com.baizhi.entity.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleDao {
    @Select("select id,title,content,status,author,create_date createDate from article limit #{start},#{rows}")
    public List<Article> queryBypage(@Param("start") Integer start, @Param("rows") Integer rows);

    @Select("select count(id) from article")
    public Integer getCount();

    @Insert("insert into article values (#{id},#{title},#{content},#{status},#{author},#{createDate})\n")
    public void insert(Article article);

    @Update("update article set title=#{title},content=#{content},status=#{status},author=#{author} where id =#{id}\n")
    public void update(Article article);

    @Select("select id,title,content,status,author,create_date createDate from article")
    public List<Article> selectAll();
}
