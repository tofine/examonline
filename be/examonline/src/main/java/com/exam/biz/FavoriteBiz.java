package com.exam.biz;

import com.exam.entity.Favorite;

public interface FavoriteBiz {
    public int setFavorite(Favorite favorite);   //添加收藏
    public int cancelFavorite(Favorite favorite); //取消收藏
}
