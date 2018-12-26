/*
package com.bing.controller;



import com.bing.response.Result;
import com.bing.utils.utilstool.IpAddressUtil;
import com.jfinal.weixin.sdk.kit.IpKit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

*/
/**
 * @author  Qing
 * @date 2018/6/3 21:12
 * @return
 *//*

@Controller
@Slf4j
public class IndexController {

  @Autowired
  private IndexService indexService;

  @Autowired
  private MailService mailService;
  */
/**
   * 首页
   *
   * @author  Qing
   * @date 2018/6/3 21:52
   * @param  * @param request
   * @param model
   * @return java.lang.String
   *//*

  @RequestMapping("/")
  public String index(HttpServletRequest request, Model model) {
    String realIp = IpKit.getRealIp(request);
    String address = IpAddressUtil.address(realIp);
    model.addAttribute("address",address);
    WebStatistics statistics = new WebStatistics();
    statistics.setIp(realIp);
    statistics.setAddress(address);
    indexService.add(statistics);
    return "/first";
  }

  */
/**
   * 冰
   *
   * @author  Qing
   * @date 2018/6/3 22:13
   * @param  * @param
   * @return java.lang.String
   *//*

  @RequestMapping("/bing")
  public String bing(){
    return "/index";
  }

  */
/**
   * 建设中
   *
   * @author  Qing
   * @date 2018/6/3 22:14
   * @param  * @param
   * @return java.lang.String
   *//*

  public String build(){
    return "/build";
  }

  */
/**
   * 發送郵件
   *
   * @author  Qing
   * @date 2018/6/5 23:27
   * @param  * @param
   * @return java.lang.String
   *//*

  @ResponseBody
  @PostMapping("/message")
  public Result sendMail(Message message){
    boolean success = indexService.addMessager(message);
    String content=Email.html.replace("%s",message.getName());
    mailService.sendHtmlMail(message.getEmail(),"很荣幸收到你提交的建议",content);
    return success?Result.SUCCESS:Result.FAIL;
  }

}

*/
