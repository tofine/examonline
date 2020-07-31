package com.exam.biz;

import com.exam.entity.Favorite;
import com.exam.entity.QuestionBase;

import java.util.ArrayList;

public interface FavoriteBiz {
    public int setFavorite(Favorite favorite);   //添加收藏
    public int cancelFavorite(Favorite favorite); //取消收藏
    public ArrayList<QuestionBase> queryFavoriteQuestionBase(int userId);
}
