package com.bing.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.bing.dto.StudentDto;
import com.bing.entity.Student;
import com.bing.mapper.StudentMapper;
import com.bing.service.StudentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 测试
 *
 * @author fzq
 * @create 2017-12-12 9:57
 */
@Service
public class StudentServiceImpl implements StudentService {

  private Integer PAGE_SIZE = 5;

  @Autowired
  private StudentMapper studentMapper;

  /**
   * @Description 根据删除
   * @Author fzq
   * @Date 2017/12/13 19:31
   */
  public int del(Integer id) {
    int del = studentMapper.deleteByPrimaryKey(id);
    return del > 0 ? 1200 : 1300;
  }


  /**
   * @Description 分页
   * @author fzq
   * @date 2018/7/10 9:00
   * @since 1.0.0
   */
  @Override
  public PageInfo<StudentDto> findHobbies(Integer pageNum, Integer id) {
    PageHelper.startPage(pageNum, PAGE_SIZE);
    List<StudentDto> hobbies = studentMapper.findHobbies(id);
    return new PageInfo<>(hobbies);
  }


}