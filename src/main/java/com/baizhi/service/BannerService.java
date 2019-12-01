package com.baizhi.service;

import com.baizhi.entity.Banner;

import java.util.List;
import java.util.Map;

public interface BannerService {

    public Map<String, Object> findAllBanner(Integer rows, Integer page);

    public void saveBanner(Banner banner);

    public void updateBanner(Banner banner);

    public void deleteBanner(String[] ids);

    public void updateBannerImg(String id, String img);

    public List<Banner> selectAll();
}
