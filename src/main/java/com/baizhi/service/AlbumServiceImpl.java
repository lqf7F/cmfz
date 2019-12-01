package com.baizhi.service;

import com.baizhi.dao.AlbumDao;
import com.baizhi.entity.Album;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    private AlbumDao albumDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String, Object> queryByPage(Integer page, Integer rows) {
        Map<String, Object> map = new HashMap<>();
        Integer records = albumDao.getCount();
        Integer start = (page - 1) * rows;
        List<Album> albums = albumDao.queryByPage(start, rows);
        Integer total = records % rows == 0 ? records / rows : records / rows + 1;
        map.put("total", total);
        map.put("records", records);
        map.put("page", page);
        map.put("rows", albums);

        return map;
    }

    @Override
    public String add(Album album) {
        String s = UUID.randomUUID().toString();
        album.setId(s);
        album.setCount(0);
        album.setPublishDate(new Date());
        album.setScore(0.0);
        albumDao.add(album);
        return s;
    }

    @Override
    public void updateUrl(Album album) {
        albumDao.updateUrl(album);
    }

    @Override
    public void updateStatus(Album album) {
        albumDao.updateStatus(album);
    }

    @Override
    public void deleteBanner(String[] ids) {
        albumDao.deleteBanner(ids);
    }

    @Override
    public List<Album> selectAll() {
        List<Album> albums = albumDao.selectAll();
        return albums;
    }
}
