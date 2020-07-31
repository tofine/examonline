package com.exam.controller;

import com.exam.biz.ExaminationBiz;
import com.exam.commons.Result;
import com.exam.dao.QuestionDao;
import com.exam.entity.ExaminationAnswer;
import com.exam.entity.ExaminationQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class ExaminationController {
    private ExaminationBiz examinationBiz;
    private QuestionDao questionDao;

    @Autowired
    public void setQuestionDao(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    @Autowired
    public void setExaminationBiz(ExaminationBiz examinationBiz) {
        this.examinationBiz = examinationBiz;
    }

    @GetMapping("/questions")
    public Result getAllExaminationQuestions(@RequestParam("baseId") int base_id){
        ArrayList<ExaminationQuestion> data=examinationBiz.getExaminationQuestionsByBase(base_id);
        return Result.success("查询成功",data.size(),data);
    }
    @GetMapping("/limitQuestions")
    public Result getLimitExaminationQuestions(@RequestParam("baseId") int base_id,@RequestParam("page") int page,@RequestParam("limit") int limit){
        ArrayList<ExaminationQuestion> data=examinationBiz.getLimitExaminationQuestionsByBase(base_id,page,limit);
        int count=questionDao.getQuestionCountByBase(base_id);
        return Result.success("查询成功",count,data);
    }

    @PostMapping("/correct/{baseId}")
    public Result getScore(@PathVariable("baseId") int baseId,@RequestBody ArrayList<ExaminationAnswer> answers){
        for(int i=0;i<answers.size();i++)
            System.out.println(answers.get(i));
        int score=examinationBiz.correct(baseId,answers);
        return Result.success("批改成功",score);
    }
}
