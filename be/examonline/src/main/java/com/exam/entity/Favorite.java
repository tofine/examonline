package com.exam.entity;

import lombok.Data;

/**
 * 收藏夹记录
 */
@Data
public class Favorite{
    Integer userId;   //用户
    Integer baseId;   //收藏的题库
}
