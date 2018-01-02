package com.pachong.pachongdemo.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class LizhiMingyan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String mingyan;
    private String type;

}
