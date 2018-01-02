package com.pachong.pachongdemo.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Member {
    //{"result":{"id":113,"phone":"15989837031","name":"tc","sex":"﻿男","idcard":"429004198807135538","driverIdcard":"429004198807135538","email":"tc","headPhotoUrl":"http://eberimg.oss-cn-shenzhen.aliyuncs.com/head/553d1fec-b7d2-4ad2-a38b-efc1c3d3cf47.jpg","driverIdcardPhotoUrl":"http://eberimg.oss-cn-shenzhen.aliyuncs.com/driverIdcard/8e012b4b-c91e-44c5-b820-b56a1e77f578.jpg","idcardPhotoUrl":null,"accoutStatus":"0","status":"ready","balance":888575578,"deposit":500000,"integral":0,"couponsNums":0,"memberLevel":"general","remark":null,"chargingId":null,"enterpriseId":null,"zhimaAuth":"0","zhimaScore":null,"token":"b6o5vgz096wjpatpi4jqjsaxytzd8md0qc75rf6mm2i2exr2umrnnlv4u9b9kkvnnjk4s74rekidmzm3mr1kvxc78agrorzn83is","isGovernmentEnterprise":"false","driverIdcardUpdate":1,"refundStatus":"","latestContributedVal":8087,"levelCode":3,"levelName":"三星会员","driverAge":27526658701,"increaseValue":0,"refundMoney":0},"method":"memberQueryInfo","code":"00","msg":"操作成功"}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String type;
    private String neirong;

}
