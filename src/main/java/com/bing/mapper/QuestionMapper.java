package com.bing.mapper;

import com.bing.entity.question.QuestionInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by hurl on 2018-08-19
 */
public interface QuestionMapper {

    public List<QuestionInfo>  getQuestionList();

    public List<String> selectQuestionIdByType(String questionType);

    public List<QuestionInfo> selectQuestionlistById(@Param("questionid") List<String> questionid);
}
