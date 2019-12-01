package com.baizhi.service;

import com.baizhi.entity.Chapter;

import java.util.List;
import java.util.Map;

public interface ChapterService {
    public Map<String, Object> queryByPage(Integer page, Integer rows, String aId);

    public String add(Chapter chapter);

    public void updateUrl(Chapter chapter);

    public void deleteChapter(String[] ids);

    public List<Chapter> selectAll();

    public Chapter selectById(String id);
}
