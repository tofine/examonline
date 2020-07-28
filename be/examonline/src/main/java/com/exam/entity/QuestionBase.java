package com.exam.entity;

import lombok.Data;

/**
 * 题库信息
 */
@Data
public class QuestionBase {
    Integer baseId;        //题库id
    String name;    //题库名字
    Integer level;      //难度等级1-5
    Integer count;      //题库中题目数量
    byte[] image;      //题库图像标识
    Integer classify;       //分在哪个栏目下（1每日练习、2专项训练、3模拟考场）
}
