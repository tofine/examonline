package com.exam.dao;

import com.exam.entity.Favorite;
import com.exam.entity.QuestionBase;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface FavoriteDao {
    public int setFavorite(Favorite favorite);   //添加收藏
    public int cancelFavorite(Favorite favorite); //取消收藏
    public ArrayList<QuestionBase> getFavoriteBaseList(Integer userId); //查询收藏夹中的题库
}
