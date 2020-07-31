package com.exam.controller;

import com.exam.biz.QuestionBiz;
import com.exam.commons.Result;
import com.exam.entity.Question;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Slf4j
@RestController
public class QuestionController {
    private QuestionBiz questionBiz;

    @Autowired
    public void setQuestionBiz(QuestionBiz questionBiz) {
        this.questionBiz = questionBiz;
    }

    @PostMapping("/question")
    public Result addQuestion(@RequestBody Question question){
        log.info("ready to insert a question ."+question);
        if(questionBiz.addQuestion(question)==1){
            return Result.success("添加成功");
        }
        return Result.error("添加失败");
    }

    @DeleteMapping("/question/{questionId}")
    public Result deleteQuestion(@PathVariable int questionId){
        if(questionBiz.deleteQuestion(questionId)==1)
            return Result.success("删除成功");
        return Result.error("删除失败");
    }

    @PutMapping("/question")
    public Result updateQuestion(@RequestBody Question question){
        if(questionBiz.updateQuestion(question)==1)
            return Result.success("更新成功");
        return Result.error("更新失败");
    }

    @GetMapping("/questionList")
    public Result getAllQuestionListByBase(@RequestParam int baseId){
        ArrayList<Question> data=questionBiz.getAllQuestionsByBase(baseId);
        if(data!=null)
            return Result.success("查询成功",data.size(),data);
        return Result.error("查询失败");
    }

    @GetMapping("/limitQuestionList")
    public Result getLimitQuestionList(@RequestParam("baseId") int baseId,@RequestParam("page") int page,@RequestParam("limit") int limit){
        ArrayList<Question> data=questionBiz.getLimitQuestionsByBase(baseId,page,limit);
        int count=questionBiz.getQuestionCountByBase(baseId);
        return Result.success("查询成功",count,data);
    }
}
