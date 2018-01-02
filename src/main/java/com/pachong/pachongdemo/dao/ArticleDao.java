package com.pachong.pachongdemo.dao;

import com.pachong.pachongdemo.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleDao extends JpaRepository<Article,Integer> {
}
