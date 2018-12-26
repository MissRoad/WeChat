package com.bing.controller;

import com.bing.authorization.annotation.WxUser;
import com.bing.constant.Constant;
import com.bing.entity.FlBind;
import com.bing.mapper.FlBindMapper;
import com.bing.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/bind")
public class BindController {

    @Autowired
    FlBindMapper flBindMapper;


    private String userName = "123456";
    private String pwd = "123456";


    /**
     * 绑定页面
     *
     * @param model
     * @param request
     * @return
     */
    @GetMapping("/index")
    @WxUser
    public String bind(Model model, HttpServletRequest request) {
        Object user = request.getSession().getAttribute(Constant.USER);
        model.addAttribute("user", user);
        return "/bind";
    }

    /**
     * 绑定用户
     *
     * @return
     */
    @PostMapping("bindUser")
    @ResponseBody
    public Result bindUser(FlBind flBind) {
        int i = flBindMapper.insertSelective(flBind);
        return i > 0 ? Result.SUCCESS : Result.FAIL;
    }

    /**
     * 用户名密码校验
     *
     * @return
     */
    @PostMapping("validate")
    @ResponseBody
    public Result validate(String flUserCode, String passWord) {
        boolean b = userName.equals(flUserCode) && pwd.equals(passWord);
        return b ? Result.SUCCESS : Result.FAIL;
    }
}
