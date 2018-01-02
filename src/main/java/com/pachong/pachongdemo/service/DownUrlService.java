package com.pachong.pachongdemo.service;


import com.pachong.pachongdemo.model.ImgDownUrl;
import com.pachong.pachongdemo.dao.ImgDownUrlDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DownUrlService {
    
    @Autowired
    ImgDownUrlDao imgDownUrlDao;
    
    public List<ImgDownUrl> findAll() {
        return imgDownUrlDao.findAll();
    }


    public List<ImgDownUrl> save(List<ImgDownUrl> var1) {
        return imgDownUrlDao.save(var1);
    }


    public ImgDownUrl saveAndFlush(ImgDownUrl var1) {
        return imgDownUrlDao.saveAndFlush(var1);
    }

    public void deleteInBatch(List<ImgDownUrl> var1) {
        imgDownUrlDao.deleteInBatch(var1);
    }

    public void deleteAllInBatch() {
        imgDownUrlDao.deleteAllInBatch();
    }

    public ImgDownUrl getOne(Integer var1) {
        return imgDownUrlDao.getOne(var1);
    }
}
