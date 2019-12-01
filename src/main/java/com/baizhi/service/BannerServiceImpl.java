package com.baizhi.service;

import com.baizhi.dao.BannerDao;
import com.baizhi.entity.Banner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerDao bannerDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String, Object> findAllBanner(Integer page, Integer rows) {
        //page:当前页，例如：page=2；
        //rows:每页展示几行，例如rows=3;
        Map<String, Object> map = new HashMap<>();
        //recodes:总条数 例如：recodes=6;
        Integer records = bannerDao.getCount();
        //total当前页数;records%rows:当前总页数;如果整除就是去整，不等于0就是多出一页，所以加一;
        //total=2
        Integer total = records % rows == 0 ? records / rows : records / rows + 1;
        //如果是第一页就是从0开始展示，就是start;如果是第二页就是从4开始展示，展示rows（3）条；
        Integer start = (page - 1) * rows;
        List<Banner> banners = bannerDao.findAllBanner(start, rows);
        map.put("total", total);
        map.put("records", records);
        map.put("page", page);
        map.put("rows", banners);
        return map;

    }

    @Override
    public void saveBanner(Banner banner) {
        banner.setTime(new Date());
        System.out.println(banner);
        bannerDao.saveBanner(banner);
    }

    @Override
    public void updateBanner(Banner banner) {
        bannerDao.updateBanner(banner);
    }

    @Override
    public void deleteBanner(String[] ids) {

        bannerDao.deleteBanner(ids);
    }

    @Override
    public void updateBannerImg(String id, String img) {
        bannerDao.updateBannerImg(id, img);
    }

    @Override
    public List<Banner> selectAll() {
        List<Banner> banners = bannerDao.selectAll();
        return banners;
    }

}
