package com.exam.entity;

/**
 * 题目实体
 */
public class Question {
    Integer QuestionId;   //题目id
    String name;      //题目描述
    String chooseA;   //选项描述
    String chooseB;
    String chooseC;
    Character answer;      //答案选项
    Integer baseId;       //题目来源于哪个题库
}
