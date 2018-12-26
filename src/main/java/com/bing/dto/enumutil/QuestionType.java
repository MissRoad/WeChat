package com.bing.dto.enumutil;

import com.bing.utils.exception.ServiceException;

/**
 * Created by hurl on 2018-08-19
 */
public enum QuestionType {

    COGNITIVE("111100000010", "认知"), //abc
    TASTE("111100000020","味道"), //bac
    ATTITUDE("111100000030","态度"),//bac
    ACTION("111100000040","行动"), //cba
    RESTRAINT("111100000050","克制");//bac

    private String code;
    private String name;

    QuestionType(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static QuestionType  getValue(String code){
        QuestionType[] values = QuestionType.values();
        for (QuestionType value:values){
            if(value.getCode().equals(code)){
                return value ;
            }
        }
        throw new ServiceException("code="+code+"找不到题目类型");
    }

    public  static  String getName(String code){
         for (QuestionType value:QuestionType.values()){
             if (value.getCode().equals(code)){
                 return value.getName();
             }
         }
         return null;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
