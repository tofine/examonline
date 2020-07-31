package com.exam.biz;

import com.exam.entity.ExaminationAnswer;
import com.exam.entity.ExaminationQuestion;

import java.util.ArrayList;

public interface ExaminationBiz {

    public ArrayList<ExaminationQuestion> getExaminationQuestionsByBase(Integer baseId);//获取考试题目
    public ArrayList<ExaminationQuestion> getLimitExaminationQuestionsByBase(Integer baseId,Integer page,Integer limit);
    public int correct(Integer baseId, ArrayList<ExaminationAnswer> answers);     //批改测试结果
}
