package com.hxxzt.bingimages.util;

import com.hxxzt.bingimages.controller.DoRequest;
import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GetBingImagesUrlUtil {

    public static String getBingImgUrl(boolean flag) {
        String imgSrc = "";
        try {
            String url = "https://cn.bing.com/HPImageArchive.aspx";
            Map params = new HashMap();//请求参数
            params.put("format", "js");
            int day = flag == true ? 0 : new Random().nextInt(10);//获取必应最近7天壁纸，必应限制只显示最近7天，随机获取，大于7，显示7的壁纸
            params.put("idx", day);
            params.put("n", "2");
            String res = DoRequest.httpGet(url, params);
            JSONObject object = JSONObject.fromObject(res);
            String imgObj = object.get("images").toString();
            if (imgObj.startsWith("[")) {
                imgObj = imgObj.substring(1, imgObj.length() - 1);
            }
            JSONObject images = JSONObject.fromObject(imgObj);
            String imgUrl = images.get("url").toString();
            imgSrc = "https://cn.bing.com" + imgUrl;
        } catch (Exception e) {//报错则返回今天的图片
            String url = "https://cn.bing.com/HPImageArchive.aspx";
            Map params = new HashMap();//请求参数
            params.put("format", "js");
            params.put("idx", "0");
            params.put("n", "1");
            String res = DoRequest.httpGet(url, params);
            JSONObject object = JSONObject.fromObject(res);
            String imgObj = object.get("images").toString();
            if (imgObj.startsWith("[")) {
                imgObj = imgObj.substring(1, imgObj.length() - 1);
            }
            JSONObject images = JSONObject.fromObject(imgObj);
            String imgUrl = images.get("url").toString();
            imgSrc = "https://cn.bing.com" + imgUrl;
        }
        return imgSrc;
    }
}