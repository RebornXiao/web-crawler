package com.pachong.pachongdemo;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class ImgUrl {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String imgUrl;
    private String fenlei;
    private String nums;
    private String fileName;


}
