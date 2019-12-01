package com.baizhi.dao;

import com.baizhi.entity.Banner;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BannerDao {
    public List<Banner> findAllBanner(@Param("start") Integer start, @Param("rows") Integer rows);

    public void saveBanner(Banner banner);

    public void updateBanner(Banner banner);

    public void deleteBanner(String[] ids);

    public void updateBannerImg(@Param("id") String id, @Param("img") String img);

    public Integer getCount();

    public List<Banner> selectAll();
}

