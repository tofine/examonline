package com.exam.biz;

import com.exam.entity.Question;

import java.util.ArrayList;

public interface QuestionBiz {
    public int addQuestion(Question question);
    public int deleteQuestion(Integer questionId);
    public int updateQuestion(Question question);
    public ArrayList<Question> getAllQuestionsByBase(Integer baseId);//获取题目所有信息
    public ArrayList<Question> getLimitQuestionsByBase(Integer baseId,Integer page,Integer limit);

    public int getQuestionCountByBase(Integer baseId);
}
