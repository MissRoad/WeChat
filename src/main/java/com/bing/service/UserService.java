package com.bing.service;

/**
 * @Description user
 * @Author fzq
 * @Date 2017/12/20 19:01
 */
public interface UserService {
    /**
     * 查询用户名是否存在
     *
     * @return int
     * @Author fzq
     * @Date 2017/12/20 19:01
     */
    int findUser(String user);

    int validate(String user, String password);
}
