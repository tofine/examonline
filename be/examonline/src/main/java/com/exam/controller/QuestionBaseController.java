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
        if(questionBaseBiz.addBase(base)==1){
            log.info("create question base successful. base id:"+base.getBaseId());
            return Result.success("题库创建成功");
        }
        return Result.error("创建失败");
    }
    @DeleteMapping("/base/{baseId}")
    public Result deleteQuestionBase(@PathVariable Integer baseId){
        if(questionBaseBiz.deleteBase(baseId)==1){
            return Result.success("删除成功");
        }
        return Result.error("删除失败");
    }

    @PutMapping("/base")
    public Result updateQuestionBase(@RequestPart(value = "file",required = false) MultipartFile file, QuestionBase base) throws IOException {
        if(file!=null)
            base.setImage(file.getBytes());
        if(questionBaseBiz.updateBase(base)==1)
            return Result.success("更新成功");
        return Result.error("更新失败");
    }

    @GetMapping("/allDailyList")
    public Result getAllDailyQuestionBaseList(){
        ArrayList<QuestionBase> data=questionBaseBiz.getAllDailyBases();
        if(data!=null)
            return Result.success("查询成功",data.size(),data);
        return Result.error("查询失败");
    }

    @GetMapping("/allSubjectList")
    public Result getAllSubjectQuestionBaseList(){
        ArrayList<QuestionBase> data=questionBaseBiz.getAllSubjectBases();
        if(data!=null)
            return Result.success("查询成功",data.size(),data);
        return Result.error("查询失败");
    }

    @GetMapping("/allMockList")
    public Result getAllMockQuestionBaseList(){
        ArrayList<QuestionBase> data=questionBaseBiz.getAllMockBases();
        if(data!=null)
            return Result.success("查询成功",data.size(),data);
        return Result.error("查询失败");
    }

    @GetMapping("/dailyList")
    public Result getLimitDailyQuestionBaseList(@RequestParam("page") int page,@RequestParam("limit") int limit){
        ArrayList<QuestionBase> data=questionBaseBiz.getLimitDailyBases(page,limit);
        if(data!=null)
            return Result.success("查询成功",questionBaseBiz.getDailyBaseCount(),data);
        return Result.error("查询失败");
    }

    @GetMapping("/subjectList")
    public Result getLimitSubjecQuestionBaseList(@RequestParam("page") int page,@RequestParam("limit") int limit){
        ArrayList<QuestionBase> data=questionBaseBiz.getLimitSubjectBases(page,limit);
        if(data!=null)
            return Result.success("查询成功",questionBaseBiz.getSubjectBaseCount(),data);
        return Result.error("查询失败");
    }

    @GetMapping("/mockList")
    public Result getLimitMockQuestionBaseList(@RequestParam("page") int page,@RequestParam("limit") int limit){
        ArrayList<QuestionBase> data=questionBaseBiz.getLimitMockBases(page,limit);
        if(data!=null)
            return Result.success("查询成功",questionBaseBiz.getMockBaseCount(),data);
        return Result.error("查询失败");
    }
}
