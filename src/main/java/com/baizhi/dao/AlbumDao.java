package com.baizhi.dao;

import com.baizhi.entity.Album;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface AlbumDao {
    @Select("select id,title,score,author,boradcast,`count`,brief,publish_date publishDate, cover,status from album limit #{start},#{rows}\n")
    public List<Album> queryByPage(@Param("start") Integer start, @Param("rows") Integer rows);

    @Select("select count(id) from album")
    public Integer getCount();

    @Insert("insert into album values (#{id},#{title},#{score},#{author},#{boradcast},#{count},#{brief},#{publishDate},#{cover},#{status})\n")
    public void add(Album album);

    @Update("update album set cover=#{cover} where id=#{id}\n")
    public void updateUrl(Album album);

    @Update("update album set title=#{title},author=#{author},boradcast=#{boradcast},brief=#{brief},status=#{status} where id=#{id}\n")
    public void updateStatus(Album album);

    public void deleteBanner(String[] ids);

    @Select("select id,title,score,author,boradcast,`count`,brief,publish_date publishDate, cover,status from album")
    public List<Album> selectAll();

}
