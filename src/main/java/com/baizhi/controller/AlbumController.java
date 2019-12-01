package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("album")
public class AlbumController {
    @Autowired
    private AlbumService albumService;

    @RequestMapping("queryByPage")
    public Map<String, Object> query(Integer page, Integer rows) {

        return albumService.queryByPage(page, rows);
    }

    @RequestMapping("edit")
    public String edit(Album album, String oper, String[] id) {
        if ("add".equals(oper)) {
            String s = albumService.add(album);
            return s;
        }
        if ("edit".equals(oper)) {
            albumService.updateStatus(album);
        } else if (oper.equals("del")) {
            albumService.deleteBanner(id);
        }

        return null;
    }

    @RequestMapping("upload")
    public void upload(MultipartFile cover, String albumId, HttpSession session) {
        String realPath = session.getServletContext().getRealPath("/img/");
        File file = new File(realPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        String filename = cover.getOriginalFilename();
        String newFileName = new Date().getTime() + "_" + filename;

        try {
            cover.transferTo(new File(file, newFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Album album = new Album();
        album.setId(albumId);
        album.setCover(newFileName);

        albumService.updateUrl(album);


    }
}
