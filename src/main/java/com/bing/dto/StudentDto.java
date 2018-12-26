package com.bing.dto;

import lombok.Data;

/**
 * @author findHobbies
 * @create 2017-12-14 10:25
 */
@Data
public class StudentDto {
    private Integer id;//学生编号
    private String name;//学生姓名
    private Integer sex;//性别
    private String hobbies;//爱好

}