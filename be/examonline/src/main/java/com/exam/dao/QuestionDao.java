package com.exam.dao;

import com.exam.entity.ExaminationAnswer;
import com.exam.entity.ExaminationQuestion;
import com.exam.entity.Question;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
@Repository
public interface QuestionDao {
    public int addQuestion(Question question);
    public int deleteQuestion(Integer questionId);
    public int updateQuestion(Question question);
    public ArrayList<Question> getAllQuestionsByBase(Integer baseId);//获取题目所有信息
    public ArrayList<Question> getLimitQuestionsByBase(Integer baseId,Integer from,Integer count);

    public int getQuestionCountByBase(Integer baseId);

    public ArrayList<ExaminationQuestion> getExaminationQuestionsByBase(Integer baseId);//获取考试题目
    public ArrayList<ExaminationQuestion> getLimitExaminationQuestionsByBase(Integer baseId,Integer from,Integer count);
    public ArrayList<ExaminationAnswer> getExaminationAnswersByBase(Integer baseId);   //获取考试答案
}
