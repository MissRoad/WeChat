package com.bing.service;

import com.bing.entity.question.GradeLevelinfo;
import com.bing.entity.question.QuestionInfo;
import com.bing.entity.question.Scoreinfo;
import com.bing.mapper.QuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hurl on 2018-08-19
 */
@Service
public class QuestionService implements QuestionMapper{

    @Autowired
    QuestionMapper questionMapper;

    @Override
    public List<QuestionInfo> getQuestionList() {
        return questionMapper.getQuestionList();
    }

    @Override
    public List<String> selectQuestionIdByType(String questionType) {
        return questionMapper.selectQuestionIdByType(questionType);
    }

    @Override
    public List<QuestionInfo> selectQuestionlistById(List<String> questionid) {
        return questionMapper.selectQuestionlistById(questionid);
    }

    public Scoreinfo calcScore(QuestionInfo questionInfo){
        int score = 0;
        int  level = 0;
        if(questionInfo.getAnswer1()!=null){
            switch (questionInfo.getQuestionType()){
                case "111100000010":
                      score = 3;
                      level = 1;
                      break;
                case "111100000020":
                      score = 2;
                      level = 2;
                      break;
                case "111100000030":
                       score = 2;
                       level = 3;
                       break;
                case "111100000040":
                       score = 1;
                       level = 4;
                       break;
                case "111100000050":
                       score = 2;
                       level = 5;
                       break;
            }
        }else if(questionInfo.getAnswer2()!=null){
            switch (questionInfo.getQuestionType()){
                case "111100000010":
                    score = 2;
                    level = 1;
                    break;
                case "111100000020":
                    score = 3;
                    level = 2;
                    break;
                case "111100000030":
                    score = 3;
                    level = 3;
                    break;
                case "111100000040":
                    score = 2;
                    level = 4;
                    break;
                case "111100000050":
                    score = 3;
                    level = 5;
                    break;
            }
        }else if(questionInfo.getAnswer3()!=null){
            switch (questionInfo.getQuestionType()){
                case "111100000010":
                    score = 1;
                    level = 1;
                    break;
                case "111100000020":
                    score = 1;
                    level = 2;
                    break;
                case "111100000030":
                    score = 1;
                    level = 3;
                    break;
                case "111100000040":
                    score = 3;
                    level = 4;
                    break;
                case "111100000050":
                    score = 1;
                    level = 5;
                    break;
            }
        }
        Scoreinfo  scoreinfo  = new Scoreinfo();
        scoreinfo.setQuestionid(questionInfo.getQuestionid());
        scoreinfo.setQuestionType(questionInfo.getQuestionType());
        scoreinfo.setScore(score);
        scoreinfo.setLevel(level);
        return  scoreinfo;
    }

    //获取文案类型
    public GradeLevelinfo getLevel(String type){
        GradeLevelinfo gradeLevelinfo = new GradeLevelinfo();
        switch (type){
            case "111100000010":
                gradeLevelinfo.setLevel("有文化的吃货");
                gradeLevelinfo.setGrade("拥有较多的吃货知识");
                gradeLevelinfo.setImgurl("http://47.94.161.170:8080/images/u171.png");
                break;
            case "111100000020":
                gradeLevelinfo.setLevel("吃货精灵");
                gradeLevelinfo.setGrade("真正的一个吃货精灵");
                gradeLevelinfo.setImgurl("http://47.94.161.170:8080/images/u171.png");
                break;
            case "111100000030":
                gradeLevelinfo.setLevel("有追求的吃货");
                gradeLevelinfo.setGrade("对美食有很高的追求");
                gradeLevelinfo.setImgurl("http://47.94.161.170:8080/images/u171.png");
                break;
            case "111100000040":
                gradeLevelinfo.setLevel("行动达人");
                gradeLevelinfo.setGrade("心动不如行动");
                gradeLevelinfo.setImgurl("http://47.94.161.170:8080/images/u171.png");
                break;
            case "111100000050":
                gradeLevelinfo.setLevel("神级吃货");
                gradeLevelinfo.setGrade("您已经成神");
                gradeLevelinfo.setImgurl("http://47.94.161.170:8080/images/u171.png");
                break;
        }
        return gradeLevelinfo;
    }
}
