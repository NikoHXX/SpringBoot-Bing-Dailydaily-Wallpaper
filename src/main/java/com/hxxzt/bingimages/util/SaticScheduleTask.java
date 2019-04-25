package com.hxxzt.bingimages.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling
public class SaticScheduleTask {

    @Value("${url}")
    private String path;

    //3.添加定时任务
    // 每天23点执行一次
    @Scheduled(cron = "0 0 23 * * ?")
    //或直接指定时间间隔，例如：5秒
//    @Scheduled(fixedRate = 5000)
    private void configureTasks() throws Exception {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateUrl = sdf.format(date).replace("-", "");

        System.err.println("执行静态定时任务时间: " + sdf1.format(date));

        URL url = new URL(GetBingImagesUrlUtil.getBingImgUrl(true)); //声明url对象
        URLConnection connection = url.openConnection(); //打开连接
        connection.setDoOutput(true);
        BufferedImage src = ImageIO.read(connection.getInputStream()); //读取连接的流，赋值给BufferedImage对象

        System.out.println("图片保存地址在:" + path + "目录下,文件名为:BING_EVERYDAY_IMAGE_" + dateUrl + ".JPEG");
        ImageIO.write(src, "JPEG", new File(path + "BING_EVERYDAY_IMAGE_" + dateUrl + ".JPEG"));
    }
}