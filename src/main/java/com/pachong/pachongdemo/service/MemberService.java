package com.pachong.pachongdemo.service;

import com.pachong.pachongdemo.dao.MemberDao;
import com.pachong.pachongdemo.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    @Autowired
    MemberDao imgUrlDao;

    public List<Member> findAll() {
        return imgUrlDao.findAll();
    }


    public List<Member> save(List<Member> var1) {
        return imgUrlDao.save(var1);
    }


    public Member saveAndFlush(Member var1) {
        return imgUrlDao.saveAndFlush(var1);
    }

    public void deleteInBatch(List<Member> var1) {
        imgUrlDao.deleteInBatch(var1);
    }

    public void deleteAllInBatch() {
        imgUrlDao.deleteAllInBatch();
    }

    public Member getOne(Integer var1) {
        return imgUrlDao.getOne(var1);
    }


}
