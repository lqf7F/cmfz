package com.baizhi.dao;


import com.baizhi.entity.Chapter;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ChapterDao {
    @Select("select id,title,size,timesize,url,album_id albumId from chapter where album_id=#{aId} limit #{start},#{rows}\n")
    public List<Chapter> queryByPage(@Param("start") Integer start, @Param("rows") Integer rows, @Param("aId") String albumId);

    @Select("select count(id) from chapter where album_id=#{aId}\n")
    public Integer getCount(String aId);

    @Insert("insert into chapter values (#{id},#{title},#{size},#{timesize},#{url},#{albumId})\n")
    public void add(Chapter chapter);

    @Update("update chapter set url=#{url},size=#{size},timesize=#{timesize} where id=#{id}\n")
    public void updateUrl(Chapter chapter);

    public void deleteChapter(String[] ids);

    @Select("select id,title,size,timesize,url,album_id albumId from chapter")
    public List<Chapter> selectAll();

    @Select("select id,title,size,timesize,url,album_id albumId from chapter where id=#{id}")
    public Chapter selectById(String id);

}
