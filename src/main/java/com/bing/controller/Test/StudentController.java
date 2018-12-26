package com.bing.controller.Test;

import com.baomidou.mybatisplus.plugins.Page;
import com.bing.dto.StudentDto;
import com.bing.entity.Student;
import com.bing.entity.User;
import com.bing.mapper.StudentMapper;
import com.bing.response.Result;
import com.bing.service.StudentService;
import com.bing.service.UserService;
import com.bing.service.impl.StudentServiceImpl;
import com.bing.service.impl.UserServiceImpl;
import com.bing.utils.redis.RedisService;
import com.github.pagehelper.PageInfo;


import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import io.swagger.annotations.ApiOperation;

/**
 * 测试controller
 *
 * @author fzq
 * @create 2018-7-15 10:00
 */
@RestController
@RequestMapping("/student")
@Component
@Aspect
public class StudentController {
  @Autowired
  StudentService studentService;

  @Autowired
  StudentMapper studentMapper;

  @Autowired
  UserService userService;


  /*
  固定每一页显示数据条数qwer
   */
  private int pageSize = 5;


  /**
   * @Description 根据id查询q
   * @Author fzq
   * @Date 2017/12/12 15:38
   */
  @PostMapping(value = "/find/{id}")
  public  List<Student> findById(@PathVariable("") Integer id, ModelMap modelMap, HttpServletRequest httpServletRequest) {
    List<Student> student = studentMapper.findByid(id);
    modelMap.addAttribute("student", student);
   // return "/lunar";
    return student;
  }

  @RequestMapping(value = "getAll")
  public List<Student> getAll(){
    List<Student> student = studentMapper.getAll();
     //modelMap.addAttribute("student",student);
    return student;
  }

  /**
   * @Description 跳转更新页面
   * @Author fzq
   * @Date 2018-7-15 15:36
   */
  @GetMapping(value = "/updatepage/{id}")
  public String updatepage(@PathVariable Integer id, Model modelMap, HttpServletRequest httpServletRequest) {
    List<Student> student = studentMapper.findByid(id);
    modelMap.addAttribute("student", student);
    return "/update";
  }

  /**
   * @Description 更新操作
   * @Author fzq
   * @Date 2017/12/12 15:40
   */
  @PostMapping("/update")
  public String update(Student student) {
    int update = studentMapper.updateById(student);
    return update > 0 ? "redirect:/student/page" : "更新失败";
  }


  /**
   * @Description 删除操作
   * @Author fzq
   * @Date 2017/12/12 15:41
   */
  @GetMapping(value = "/del/{id}")
  public String del(@PathVariable Integer id) {
    studentService.del(id);
    return "redirect:/student/page";
  }

  /**
   * @Description 跳转添加页
   * @Author fzq
   * @Date 2017/12/12 19:44
   */
  @GetMapping(value = "/add")
  public String trans() {
    return "/add";
  }

  /**
   * @Description 添加操作
   * @Author fzq
   * @Date 2017/12/12 19:45
   */
  @PostMapping(value = "/save")
  public String save(Student student) {
    int save = studentMapper.insert(student);
    return save > 0 ? "redirect:/student/page" : "保存失败";
  }


  /**
   * @Description 多表联查
   * @Author fzq
   * @Date 2017/12/14 10:50
   */
  @ApiOperation(value = "多表联查学生爱好", notes = "分页")
  @RequestMapping(value = "/page")
  public String page(Model model, @RequestParam(defaultValue = "1") Integer pageNum, Integer id) {
    PageInfo<StudentDto> hobbies = studentService.findHobbies(pageNum, id);
    model.addAttribute("student", hobbies);
    return "/lunar";
  }

  @GetMapping("/index")
  public String index() {
    return "/login";
  }



/**
   * @Description 登录
   * @Author fzq
   * @Date 2017/12/21 16:01
   */

  @RequestMapping("/login")
  @ResponseBody
  public Result login(String user, String password, HttpSession httpSession) {
    int result = userService.findUser(user);
    if (result == 0) {
      return new Result(13000, "账号不存在");
    }
    result = userService.validate(user, password);
    if (result > 0) {
      httpSession.setAttribute("login", user);
    }
    return result == 0 ? new Result(13000, "密码错误") : Result.SUCCESS;
  }


  /**
   * @Description 退出登录
   * @Author fzq
   * @Date 2017/12/21 10:18
   */
  @RequestMapping("/exit")
  public String exit(HttpSession httpSession) {
    httpSession.removeAttribute("login");
    return "redirect:/student/index";
  }
}