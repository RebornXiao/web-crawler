package com.pachong.pachongdemo.dao;


import com.pachong.pachongdemo.model.ImgHaveUrl;
import com.pachong.pachongdemo.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberDao extends JpaRepository<Member,Integer> {
}
