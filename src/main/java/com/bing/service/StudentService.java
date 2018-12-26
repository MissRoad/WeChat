package com.bing.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.bing.dto.StudentDto;
import com.bing.entity.Student;
import com.github.pagehelper.PageInfo;


public interface StudentService {



    /**
     * @Description 分页
     * @author fzq
     * @date 2018/7/10 9:00
     * @since 1.0.0
     */
    PageInfo<StudentDto> findHobbies(Integer pageNum,Integer id);

    /**
     * @Description
     * @author fzq
     * @date 2018/7/10 9:20
     * @since 1.0.0
     */
    int del(Integer id);
}
