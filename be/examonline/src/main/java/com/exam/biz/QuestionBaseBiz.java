package com.exam.biz;

import com.exam.entity.QuestionBase;

import java.util.ArrayList;

public interface QuestionBaseBiz {
    public ArrayList<QuestionBase> getAllDailyBases();  //获取日常训练题库列表
    public ArrayList<QuestionBase>  getLimitDailyBases(int page,int limit);
    public int getDailyBaseCount();
    public ArrayList<QuestionBase>  getAllSubjectBases(); //专题训练题库
    public ArrayList<QuestionBase>  getLimitSubjectBases(int page,int limit);
    public int getSubjectBaseCount();
    public ArrayList<QuestionBase>  getAllMockBases();    //模拟考试题库
    public ArrayList<QuestionBase>  getLimitMockBases(int page,int limit);
    public int getMockBaseCount();

    public int addBase(QuestionBase base);          //添加题库
    public int updateBase(QuestionBase base);           //更新题库信息
    public int deleteBase(int baseId);             //删除题库
}
