package com.bing.controller;

import com.google.common.collect.Lists;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.material.WxMpMaterial;
import me.chanjar.weixin.mp.bean.material.WxMpMaterialCountResult;
import me.chanjar.weixin.mp.bean.material.WxMpMaterialNews;
import me.chanjar.weixin.mp.bean.material.WxMpMaterialUploadResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * 上传素材
 *
 * @author fzq
 * @create 2018-01-10 14:57
 */
@RestController
@RequestMapping("/file")
public class WxFileController {
    @Autowired
    private WxMpService wxMpService;

    /**
     * {"mediaId":"Pv8clRu6_DhMPYQMtwGvuPSZXAwLnZ2Wxxhixm35UHc","url":"http://mmbiz.qpic.cn/mmbiz_jpg/ngvkbar6w1ibBOVuia9ImmTgWfnD9mcTauGgy2wQELQExr0R4sePHMx7Jlc2nqpViaN5fVkh65sWVHibCXjFkR2wCg/0?wx_fmt=jpeg","errCode":null,"errMsg":null}
     *
     * @Description 上传图片(微信永久素材)
     * @Author fzq
     * @Date 2018/1/10 15:56
     */
    @GetMapping(value = "/image")
    public WxMpMaterialUploadResult uploadImage() throws WxErrorException {
        //        String asToken = String.format("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s", appid, secret);
//        String token = restTemplate.getForObject(asToken, String.class);
//        Map<String, Object> info = JsonMapper.nonDefaultMapper().fromJson(token, Map.class);
//        String access_token = (String) info.get("ACCESS_TOKEN");
//        String type = "image";
//        InputStream is = new FileInputStream(file);
//        String url = String.format("https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=%s&type=%s", access_token, type);
//        URI result = restTemplate.postForLocation(url, request);
        File file = new File("C:\\Users\\spider\\Desktop\\图片\\新建文件夹\\banner750x720.jpg");
        WxMpMaterial wxMpMaterial = new WxMpMaterial();
        wxMpMaterial.setFile(file);
        wxMpMaterial.setName("test");
        System.out.println(this.wxMpService.getMaterialService());
        WxMpMaterialUploadResult s = this.wxMpService.getMaterialService().materialFileUpload("image", wxMpMaterial);
        return s;
    }

    /**
     * {"mediaId":"Pv8clRu6_DhMPYQMtwGvuDmaKhtENthm82PtVa6Lirk","url":null,"errCode":null,"errMsg":null}
     *
     * @Description 新增永久图文素材
     * @Author fzq
     * @Date 2018/1/11 10:38
     */
    @GetMapping(value = "/addNews")
    public WxMpMaterialUploadResult addNews() throws WxErrorException {
        List<WxMpMaterialNews.WxMpMaterialNewsArticle> articles = Lists.newArrayList();
        WxMpMaterialNews.WxMpMaterialNewsArticle newsArticle = new WxMpMaterialNews.WxMpMaterialNewsArticle();
        newsArticle.setTitle("我是MT");//标题
        newsArticle.setThumbMediaId("Pv8clRu6_DhMPYQMtwGvuPSZXAwLnZ2Wxxhixm35UHc");//素材id
        newsArticle.setAuthor("Lunar");//作者
        newsArticle.setDigest("娃哈哈哈哈哈哈哈哈哈哈矿泉水");//图文消息的摘要
        newsArticle.setShowCoverPic(true);//图文消息的摘要
        newsArticle.setContent("老板，你这里卖的有哇哈哈哈哈哈哈哈哈哈矿泉水吗？^_^||");//内容
        newsArticle.setContentSourceUrl("www.wahaha.com");//图文消息的原文地址
        newsArticle.setShowCoverPic(true);//是否显示封面
        articles.add(newsArticle);
        WxMpMaterialNews news = new WxMpMaterialNews();
        news.setArticles(articles);
        Date date = new Date();
        news.setCreateTime(date);
        WxMpMaterialUploadResult wx = this.wxMpService.getMaterialService().materialNewsUpload(news);
        return wx;
    }

    /**
     * @Description 获取素材总数
     * @Author fzq
     * @Date 2018/1/15 13:51
     */
    @GetMapping(value = "/fileCount")
    public WxMpMaterialCountResult fileCount() throws WxErrorException {
        WxMpMaterialCountResult wxMpMaterialCountResult = this.wxMpService.getMaterialService().materialCount();
        return wxMpMaterialCountResult;
    }

}