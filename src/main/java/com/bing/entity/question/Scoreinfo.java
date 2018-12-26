package com.bing.entity.question;

/**
 * Created by hurl on 2018-08-26
 * 题目 类型 分数
 */
public class Scoreinfo {
    private  int questionid;
    private  String questionType;
    private  int score;
    private  int level ;

    public int getQuestionid() {
        return questionid;
    }

    public void setQuestionid(int questionid) {
        this.questionid = questionid;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
