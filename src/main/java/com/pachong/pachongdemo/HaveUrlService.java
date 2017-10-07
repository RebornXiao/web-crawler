package com.pachong.pachongdemo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HaveUrlService {
    
    @Autowired
    ImgHaveUrlDao imgHaveUrlDao;
    
    public List<ImgHaveUrl> findAll() {
        return imgHaveUrlDao.findAll();
    }


    public List<ImgHaveUrl> save(List<ImgHaveUrl> var1) {
        return imgHaveUrlDao.save(var1);
    }


    public ImgHaveUrl saveAndFlush(ImgHaveUrl var1) {
        return imgHaveUrlDao.saveAndFlush(var1);
    }

    public void deleteInBatch(List<ImgHaveUrl> var1) {
        imgHaveUrlDao.deleteInBatch(var1);
    }

    public void deleteAllInBatch() {
        imgHaveUrlDao.deleteAllInBatch();
    }

    public ImgHaveUrl getOne(Integer var1) {
        return imgHaveUrlDao.getOne(var1);
    }
}
