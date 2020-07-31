package com.exam.biz.impl;

import com.exam.biz.QuestionBiz;
import com.exam.dao.QuestionDao;
import com.exam.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
@Service
public class QuestionBizImpl implements QuestionBiz {
    private QuestionDao questionDao;

    @Autowired
    public void setQuestionDao(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    @Override
    @CacheEvict(value = "question",allEntries = true)
    @Transactional
    public int addQuestion(Question question) {
        return questionDao.addQuestion(question);
    }

    @Override
    @CacheEvict(value = "question",allEntries = true)
    @Transactional
    public int deleteQuestion(Integer questionId) {
        return questionDao.deleteQuestion(questionId);
    }

    @Override
    @CacheEvict(value = "question",allEntries = true)
    @Transactional
    public int updateQuestion(Question question) {
        return questionDao.updateQuestion(question);
    }

    @Override
    @Cacheable(value = "question",key="'getAllQuestionsByBase'+#baseId")
    @Transactional
    public ArrayList<Question> getAllQuestionsByBase(Integer baseId) {
        return questionDao.getAllQuestionsByBase(baseId);
    }

    @Override
    @Cacheable(value="question",key="'getLimitQuestionsByBase'+#baseId+','+#page+','+#limit")
    @Transactional
    public ArrayList<Question> getLimitQuestionsByBase(Integer baseId, Integer page, Integer limit) {
        return questionDao.getLimitQuestionsByBase(baseId,(page-1)*limit,limit);
    }

    @Override
    @Transactional
    public int getQuestionCountByBase(Integer baseId) {
        return questionDao.getQuestionCountByBase(baseId);
    }

}
