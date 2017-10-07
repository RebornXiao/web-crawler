package com.pachong.pachongdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImgService {

    @Autowired
    ImgUrlDao imgUrlDao;

    public List<ImgUrl> findAll() {
        return imgUrlDao.findAll();
    }


    public List<ImgUrl> save(List<ImgUrl> var1) {
        return imgUrlDao.save(var1);
    }


    public ImgUrl saveAndFlush(ImgUrl var1) {
        return imgUrlDao.saveAndFlush(var1);
    }

    public void deleteInBatch(List<ImgUrl> var1) {
        imgUrlDao.deleteInBatch(var1);
    }

    public void deleteAllInBatch() {
        imgUrlDao.deleteAllInBatch();
    }

    public ImgUrl getOne(Integer var1) {
        return imgUrlDao.getOne(var1);
    }


}
