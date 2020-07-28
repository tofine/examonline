package com.exam.dao;

import com.exam.entity.QuestionBase;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
@Repository
public interface QuestionBaseDao {
    public ArrayList<QuestionBase> getAllDailyBases();  //日常训练题库
    public ArrayList<QuestionBase> getLimitDailyBases(int from,int count);
    public int getDailyBaseCount();
    public ArrayList<QuestionBase> getAllSubjectBases(); //专题训练题库
    public ArrayList<QuestionBase> getLimitSubjectBases(int from,int count);
    public int getSubjectBaseCount();
    public ArrayList<QuestionBase> getAllMockBases();    //模拟考试题库
    public ArrayList<QuestionBase> getLimitMockBases(int from,int count);
    public int getMockBaseCount();

    public int addBase(QuestionBase base);          //添加题库
    public int baseQuestionCountIncrement(int baseId);   //某个题库题目数量增加1
    public int updateBase(QuestionBase base);           //更新题库信息
    public int deleteBase(int baseId);             //删除题库
}
