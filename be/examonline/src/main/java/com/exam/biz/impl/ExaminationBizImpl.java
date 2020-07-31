package com.exam.biz.impl;

import com.exam.biz.ExaminationBiz;
import com.exam.dao.QuestionDao;
import com.exam.entity.ExaminationAnswer;
import com.exam.entity.ExaminationQuestion;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
@Slf4j
@Service
public class ExaminationBizImpl implements ExaminationBiz {
    private QuestionDao questionDao;

    @Autowired
    public void setQuestionDao(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    @Override
    @Cacheable(value ="question",key="'getExaminationQuestionsByBase'+#baseId")
    @Transactional
    public ArrayList<ExaminationQuestion> getExaminationQuestionsByBase(Integer baseId) {
        return questionDao.getExaminationQuestionsByBase(baseId);
    }

    @Override
    @Cacheable(value = "question",key="'getLimitExaminationQuestionsByBase'+#baseId+','+#page+','+#limit")
    @Transactional
    public ArrayList<ExaminationQuestion> getLimitExaminationQuestionsByBase(Integer baseId, Integer page, Integer limit) {
        return questionDao.getLimitExaminationQuestionsByBase(baseId,(page-1)*limit,limit);
    }

    @Override
    @Transactional
    public int correct(Integer baseId, ArrayList<ExaminationAnswer> answers) {
        int sumScores=0;
        ArrayList<ExaminationAnswer> answersByBase = questionDao.getExaminationAnswersByBase(baseId);
        for(int i=0;i<answers.size();i++){
            ExaminationAnswer answer=answers.get(i);
            ExaminationAnswer answer1=answersByBase.get(i);
            if(answer.equals(answer1)){
                sumScores+=answer1.getScore();
            }
        }
        return sumScores;
    }
}
