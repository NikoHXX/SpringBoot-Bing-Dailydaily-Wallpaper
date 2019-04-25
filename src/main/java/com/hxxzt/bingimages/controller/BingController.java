package com.hxxzt.bingimages.controller;

import com.hxxzt.bingimages.util.GetBingImagesUrlUtil;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.net.URLConnection;

@Controller
@EnableAutoConfiguration
public class BingController {
    //获取Bing壁纸URL，创建图片，返回到页面
    //类似获取验证码
    @RequestMapping("/")
    public ModelAndView handleRequest(HttpServletResponse response) throws Exception {
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");

        URL url = new URL(GetBingImagesUrlUtil.getBingImgUrl(true)); //声明url对象
        URLConnection connection = url.openConnection(); //打开连接
        connection.setDoOutput(true);
        BufferedImage src = ImageIO.read(connection.getInputStream()); //读取连接的流，赋值给BufferedImage对象
        //输出图象到页面
        ImageIO.write(src, "JPEG", response.getOutputStream());
        ServletOutputStream out = response.getOutputStream();
        try {
            out.flush();
        } finally {
            out.close();
        }
        return null;
    }

}