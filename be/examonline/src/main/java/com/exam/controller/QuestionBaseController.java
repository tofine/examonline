package com.exam.controller;

import com.exam.biz.QuestionBaseBiz;
import com.exam.commons.Result;
import com.exam.entity.QuestionBase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;

@Slf4j
@RestController
public class QuestionBaseController {
    private QuestionBaseBiz questionBaseBiz;

    @Autowired
    public void setQuestionBaseBiz(QuestionBaseBiz questionBaseBiz) {
        this.questionBaseBiz = questionBaseBiz;
    }

    @PostMapping("/base")
    public Result addQuestionBase(@RequestPart(value = "file",required = false) MultipartFile file, QuestionBase base) throws IOException {
        if(file!=null)
            base.setImage(file.getBytes());
        questionBaseBiz.addBase(base);
        log.info("create question base successful. base id:"+base.getBaseId());
        return Result.success("题库创建成功");
    }
    @DeleteMapping("/base/{baseId}")
    public Result deleteQuestionBase(@PathVariable Integer baseId){
        questionBaseBiz.deleteBase(baseId);
        return Result.success("删除成功");
    }

    @PutMapping("/base")
    public Result updateQuestionBase(@RequestPart(value = "file",required = false) MultipartFile file, QuestionBase base) throws IOException {
        if(file!=null)
            base.setImage(file.getBytes());
        questionBaseBiz.updateBase(base);
        return Result.success("更新成功");
    }

    @GetMapping("/allDailyList")
    public Result getAllDailyQuestionBaseList(){
        ArrayList<QuestionBase> data=questionBaseBiz.getAllDailyBases();
        return Result.success("查询成功",data.size(),data);
    }

    @GetMapping("/allSubjectList")
    public Result getAllSubjectQuestionBaseList(){
        ArrayList<QuestionBase> data=questionBaseBiz.getAllSubjectBases();
        return Result.success("查询成功",data.size(),data);
    }

    @GetMapping("/allMockList")
    public Result getAllMockQuestionBaseList(){
        ArrayList<QuestionBase> data=questionBaseBiz.getAllMockBases();
        return Result.success("查询成功",data.size(),data);
    }

    @GetMapping("/dailyList")
    public Result getLimitDailyQuestionBaseList(@RequestParam("page") int page,@RequestParam("limit") int limit){
        ArrayList<QuestionBase> data=questionBaseBiz.getLimitDailyBases(page,limit);
        return Result.success("查询成功",questionBaseBiz.getDailyBaseCount(),data);
    }

    @GetMapping("/subjectList")
    public Result getLimitSubjecQuestionBaseList(@RequestParam("page") int page,@RequestParam("limit") int limit){
        ArrayList<QuestionBase> data=questionBaseBiz.getLimitSubjectBases(page,limit);
        return Result.success("查询成功",questionBaseBiz.getSubjectBaseCount(),data);
    }

    @GetMapping("/mockList")
    public Result getLimitMockQuestionBaseList(@RequestParam("page") int page,@RequestParam("limit") int limit){
        ArrayList<QuestionBase> data=questionBaseBiz.getLimitMockBases(page,limit);
        return Result.success("查询成功",questionBaseBiz.getMockBaseCount(),data);
    }
}
