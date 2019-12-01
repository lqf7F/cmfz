package com.baizhi.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.dao.BannerDao;
import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("banner")
public class BannerController {
    @Autowired
    BannerDao bannerDao;
    @Autowired
    private BannerService bannerService;

    @RequestMapping("selectAll")
    public Map<String, Object> findAll(Integer page, Integer rows) {
        System.out.println("page = " + page);
        System.out.println("rows = " + rows);
        return bannerService.findAllBanner(page, rows);
    }

    @RequestMapping("edit")
    public String edit(Banner banner, String oper, String img, String[] id) {
        if (oper.equals("add")) {
            String s = UUID.randomUUID().toString();
            banner.setId(s);
            bannerService.saveBanner(banner);
            return s;
        } else if (oper.equals("edit")) {

            bannerService.updateBanner(banner);

        } else if (oper.equals("del")) {
            bannerService.deleteBanner(id);
        }
        return null;
    }


    @RequestMapping("upload")
    public void upload(MultipartFile img, String id, HttpSession session) {
        String realPath = session.getServletContext().getRealPath("/img/");
        File file = new File(realPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        String filename = img.getOriginalFilename();
        String newFileName = new Date().getTime() + "_" + filename;
        System.out.println(img + "=====" + id);

        try {
            img.transferTo(new File(file, newFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(id);
        System.out.println(newFileName + "-------lqf--------" + file);
        bannerService.updateBannerImg(id, newFileName);
    }

    @RequestMapping("select")
    public String select() {
        List<Banner> banners = bannerDao.selectAll();
        for (Banner banner : banners) {
            System.out.println("banner = " + banner);
        }
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("轮播图", "轮播", "aa"),
                Banner.class, banners);
        try {
            workbook.write(new FileOutputStream(new File("G:/banner.xls")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "main.jsp";
    }
}
