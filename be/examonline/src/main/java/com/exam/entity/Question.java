package com.exam.entity;

import lombok.Data;

/**
 * 题目实体
 */
@Data
public class Question {
    Integer questionId;   //题目id
    String name;      //题目描述
    String chooseA;   //选项描述
    String chooseB;
    String chooseC;
    Character answer;      //答案选项
    Integer baseId;       //题目来源于哪个题库
    Float score;       //分数
}
