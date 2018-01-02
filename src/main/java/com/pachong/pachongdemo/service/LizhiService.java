package com.pachong.pachongdemo.service;

import com.pachong.pachongdemo.dao.LizhiDao;
import com.pachong.pachongdemo.model.LizhiMingyan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LizhiService {

    @Autowired
    LizhiDao lizhiDao;

    public List<LizhiMingyan> findAll() {
        return lizhiDao.findAll();
    }


    public List<LizhiMingyan> save(List<LizhiMingyan> var1) {
        return lizhiDao.save(var1);
    }


    public LizhiMingyan saveAndFlush(LizhiMingyan var1) {
        return lizhiDao.saveAndFlush(var1);
    }

    public void deleteInBatch(List<LizhiMingyan> var1) {
        lizhiDao.deleteInBatch(var1);
    }

    public void deleteAllInBatch() {
        lizhiDao.deleteAllInBatch();
    }

    public LizhiMingyan getOne(Integer var1) {
        return lizhiDao.getOne(var1);
    }


}
