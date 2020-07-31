package com.exam.entity;

import lombok.Data;

/**
 * 试题答案
 */
@Data
public class ExaminationAnswer {
    Integer questionId;   //题目id
    Character answer;     //答案
    Float score;         //分值

    public boolean equals(ExaminationAnswer examinationAnswer){
        if(this.questionId==(examinationAnswer.questionId)&&this.answer==examinationAnswer.answer)
            return true;
        else return false;
    }

    @Override
    public String toString() {
        return "ExaminationAnswer{" +
                "QuestionId=" + questionId +
                ", answer=" + answer +
                ", score=" + score +
                '}';
    }
}
