package com.exam.controller;

import com.exam.biz.FavoriteBiz;
import com.exam.commons.Result;
import com.exam.entity.Favorite;
import com.exam.entity.QuestionBase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Slf4j
@RestController
public class FavoriteController {
    private FavoriteBiz favoriteBiz;

    @Autowired
    public void setFavoriteBiz(FavoriteBiz favoriteBiz) {
        this.favoriteBiz = favoriteBiz;
    }

    @PostMapping("/favorite")
    public Result setFavorite(@RequestBody Favorite favorite){
        if(favoriteBiz.setFavorite(favorite)==1){
            log.info("add a favorite record.  user:"+favorite.getUserId()+" base:"+favorite.getBaseId());
            return Result.success("收藏成功");
        }
        return Result.error("收藏失败");
    }

    @DeleteMapping("/favorite")
    public Result cancelFavorite(@RequestBody Favorite favorite){
        if(favoriteBiz.cancelFavorite(favorite)==1){
            log.info("delete a favorite record.  user:"+favorite.getUserId()+" base:"+favorite.getBaseId());
            return Result.success("取消收藏成功");
        }
        return Result.error("取消收藏失败");
    }

    @GetMapping("/favoriteBases") //查询收藏的题库列表
    public Result getFavorites(@RequestParam("userId") int userId){
        ArrayList<QuestionBase> data=favoriteBiz.queryFavoriteQuestionBase(userId);
        return Result.success("查询成功",data.size(),data);
    }
}
