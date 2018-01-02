package com.pachong.pachongdemo.dao;

import com.pachong.pachongdemo.model.LizhiMingyan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LizhiDao extends JpaRepository<LizhiMingyan,Integer> {
}
