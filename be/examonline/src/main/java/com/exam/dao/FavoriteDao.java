package com.exam.dao;

import com.exam.entity.Favorite;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteDao {
    public int setFavorite(Favorite favorite);   //添加收藏
    public int cancelFavorite(Favorite favorite); //取消收藏
}
