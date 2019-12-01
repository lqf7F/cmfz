package com.baizhi.service;

import com.baizhi.entity.Album;

import java.util.List;
import java.util.Map;

public interface AlbumService {
    public Map<String, Object> queryByPage(Integer page, Integer rows);

    public String add(Album album);

    public void updateUrl(Album album);

    public void updateStatus(Album album);

    public void deleteBanner(String[] ids);

    public List<Album> selectAll();
}
