package com.baizhi.controller;

import com.baizhi.util.ValidateImageCodeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
@RequestMapping("code")
public class ImgCodeController {
    @RequestMapping("code")
    public void getCode(HttpServletResponse response, HttpSession session) {
        String securityCode = ValidateImageCodeUtils.getSecurityCode();
        session.setAttribute("imgCode", securityCode);
        BufferedImage image = ValidateImageCodeUtils.createImage(securityCode);
        ServletOutputStream stream = null;
        try {
            stream = response.getOutputStream();
            ImageIO.write(image, "png", stream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
