package com.exam.entity;

import lombok.Data;

/**
 * 考试题目
 */
@Data
public class ExaminationQuestion {
    Integer QuestionId;   //题目id
    String name;      //题目描述
    String chooseA;   //选项描述
    String chooseB;
    String chooseC;
}
