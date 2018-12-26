package com.bing.service.impl;



import com.bing.mapper.UserMapper;
import com.bing.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author fzq
 * @create 2017-12-20 19:01
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    /**
     * @Description 查询用户名是否存在
     * @Author fzq
     * @Date 2017/12/20 19:39
     */
    @Override
    public int findUser(String user) {
        return userMapper.findUser(user);
    }

    /**
     * @Description 登录验证
     * @Author fzq
     * @Date 2017/12/20 19:39
     */
    @Override
    public int validate(String user, String password) {
        return userMapper.validate(user, password);
    }
}