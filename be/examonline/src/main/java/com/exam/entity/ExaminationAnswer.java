package com.exam.entity;

import lombok.Data;

/**
 * 试题答案
 */
@Data
public class ExaminationAnswer {
    Integer QuestionId;   //题目id
    Character answer;     //答案
}
