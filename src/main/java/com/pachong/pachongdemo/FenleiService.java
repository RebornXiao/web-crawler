package com.pachong.pachongdemo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FenleiService {
    
    @Autowired
    ImgFenleiDao imgFenleiDao;
    
    public List<ImgFenlei> findAll() {
        return imgFenleiDao.findAll();
    }


    public List<ImgFenlei> save(List<ImgFenlei> var1) {
        return imgFenleiDao.save(var1);
    }


    public ImgFenlei saveAndFlush(ImgFenlei var1) {
        return imgFenleiDao.saveAndFlush(var1);
    }

    public void deleteInBatch(List<ImgFenlei> var1) {
        imgFenleiDao.deleteInBatch(var1);
    }

    public void deleteAllInBatch() {
        imgFenleiDao.deleteAllInBatch();
    }

    public ImgFenlei getOne(Integer var1) {
        return imgFenleiDao.getOne(var1);
    }
}
