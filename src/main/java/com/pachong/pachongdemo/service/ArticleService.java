package com.pachong.pachongdemo.service;

import com.pachong.pachongdemo.dao.ArticleDao;
import com.pachong.pachongdemo.model.Article;
import com.pachong.pachongdemo.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    @Autowired
    ArticleDao articleDao;

    public List<Article> findAll() {
        return articleDao.findAll();
    }


    public List<Article> save(List<Article> var1) {
        return articleDao.save(var1);
    }


    public Article saveAndFlush(Article var1) {
        return articleDao.saveAndFlush(var1);
    }

    public void deleteInBatch(List<Article> var1) {
        articleDao.deleteInBatch(var1);
    }

    public void deleteAllInBatch() {
        articleDao.deleteAllInBatch();
    }

    public Article getOne(Integer var1) {
        return articleDao.getOne(var1);
    }


}
