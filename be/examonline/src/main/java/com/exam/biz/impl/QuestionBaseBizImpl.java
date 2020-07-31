package com.exam.biz.impl;

import com.exam.biz.QuestionBaseBiz;
import com.exam.dao.QuestionBaseDao;
import com.exam.entity.QuestionBase;
import com.exam.exception.DuplicateKeyException;
import com.exam.exception.TargetResourceNotExitException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class QuestionBaseBizImpl implements QuestionBaseBiz {

    private QuestionBaseDao questionBaseDao;

    @Autowired
    public void setQuestionBaseDao(QuestionBaseDao questionBaseDao) {
        this.questionBaseDao = questionBaseDao;
    }

    @Override
    @Cacheable(value = "questionBase",key = "'getAllDailyBases'")
    @Transactional
    public ArrayList<QuestionBase> getAllDailyBases() {
        return questionBaseDao.getAllDailyBases();
    }

    @Override
    @Cacheable(value = "questionBase",key="'getLimitDailyBases'+#page+','+#limit")
    @Transactional
    public ArrayList<QuestionBase> getLimitDailyBases(int page, int limit) {
        return questionBaseDao.getLimitDailyBases((page-1)*limit,limit);
    }

    @Override
    @Cacheable(value = "questionBase",key="'getDailyBaseCount'")
    @Transactional
    public int getDailyBaseCount() {
        return questionBaseDao.getDailyBaseCount();
    }

    @Override
    @Cacheable(value = "questionBase",key = "'getAllSubjectBases'")
    @Transactional
    public ArrayList<QuestionBase> getAllSubjectBases() {
        return questionBaseDao.getAllSubjectBases();
    }

    @Override
    @Cacheable(value="questionBase",key="'getLimitSubjectBases'+#page+','+#limit")
    @Transactional
    public ArrayList<QuestionBase> getLimitSubjectBases(int page, int limit) {
        return questionBaseDao.getLimitSubjectBases((page-1)*limit,limit);
    }

    @Override
    @Cacheable(value="questionBase",key="'getSunbjectBaseCount'")
    @Transactional
    public int getSubjectBaseCount() {
        return questionBaseDao.getSubjectBaseCount();
    }

    @Override
    @Cacheable(value="questionBase",key="'getAllMockBases'")
    @Transactional
    public ArrayList<QuestionBase> getAllMockBases() {
        return questionBaseDao.getAllMockBases();
    }

    @Override
    @Cacheable(value = "questionBase",key="'getLimitMockBases'+#page+','+#limit")
    @Transactional
    public ArrayList<QuestionBase> getLimitMockBases(int page, int limit) {
        return questionBaseDao.getLimitMockBases((page-1)*limit,limit);
    }

    @Override
    @Transactional
    @Cacheable(value="questionBase",key="'getLimitMockBases'+#page+','+#limit")
    public int getMockBaseCount() {
        return questionBaseDao.getMockBaseCount();
    }

    @Override
    @CacheEvict(value = "questionBase",allEntries = true)
    @Transactional
    public int addBase(QuestionBase base) {
        try{
            return questionBaseDao.addBase(base);
        }catch (RuntimeException e){
            throw new DuplicateKeyException();
        }
    }

    @Override
    @CacheEvict(value = "questionBase",allEntries = true)
    @Transactional
    public int updateBase(QuestionBase base) {
        int i = questionBaseDao.updateBase(base);
        if(i==0) throw new TargetResourceNotExitException();
        return i;
    }

    @Override
    @CacheEvict(value = "questionBase",allEntries = true)
    @Transactional
    public int deleteBase(int baseId) {
        int i= questionBaseDao.deleteBase(baseId);
        if(i==0) throw new TargetResourceNotExitException();
        return i;
    }
}
