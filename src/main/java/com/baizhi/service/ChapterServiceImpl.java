package com.baizhi.service;

import com.baizhi.dao.ChapterDao;
import com.baizhi.entity.Chapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
public class ChapterServiceImpl implements ChapterService {
    @Autowired
    private ChapterDao chapterDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String, Object> queryByPage(Integer page, Integer rows, String aId) {
        Map<String, Object> map = new HashMap<>();
        Integer records = chapterDao.getCount(aId);
        Integer start = (page - 1) * rows;
        List<Chapter> chapters = chapterDao.queryByPage(start, rows, aId);
        Integer total = records % rows == 0 ? records / rows : records / rows + 1;
        map.put("total", total);
        map.put("records", records);
        map.put("page", page);
        map.put("rows", chapters);
        return map;
    }

    @Override
    public String add(Chapter chapter) {

        String s = UUID.randomUUID().toString();
        chapter.setId(s);
        chapterDao.add(chapter);
        return s;
    }

    @Override
    public void updateUrl(Chapter chapter) {
        chapterDao.updateUrl(chapter);
    }

    @Override
    public void deleteChapter(String[] ids) {
        chapterDao.deleteChapter(ids);
    }

    @Override
    public List<Chapter> selectAll() {
        List<Chapter> chapters = chapterDao.selectAll();
        return chapters;
    }

    @Override
    public Chapter selectById(String id) {
        Chapter chapter = chapterDao.selectById(id);
        return chapter;
    }
}
