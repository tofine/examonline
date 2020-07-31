package com.exam.biz.impl;

import com.exam.biz.FavoriteBiz;
import com.exam.dao.FavoriteDao;
import com.exam.entity.Favorite;
import com.exam.entity.QuestionBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class FavoriteBizImpl implements FavoriteBiz {
    private FavoriteDao favoriteDao;

    @Autowired
    public void setFavoriteDao(FavoriteDao favoriteDao) {
        this.favoriteDao = favoriteDao;
    }

    @Override
    @Transactional
    public int setFavorite(Favorite favorite) {
        return favoriteDao.setFavorite(favorite);
    }

    @Override
    @Transactional
    public int cancelFavorite(Favorite favorite) {
        return favoriteDao.cancelFavorite(favorite);
    }

    @Override
    public ArrayList<QuestionBase> queryFavoriteQuestionBase(int userId) {
        return favoriteDao.getFavoriteBaseList(userId);
    }
}
