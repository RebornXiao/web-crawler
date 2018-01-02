package com.pachong.pachongdemo.utils;

import org.springframework.util.StringUtils;

/**
 * Created by 肖建伟 on 2017/12/26.
 */
public class NeirongUtils {
    public static String splitNeirong(String neirong){
        neirong = isBefore(neirong);
        neirong = isAfter(neirong);
        return neirong;
    }
    public static String isBefore(String neirong){
        if(StringUtils.isEmpty(neirong))
        {
            return neirong;
        }
        if(neirong.startsWith("、")){
            neirong = neirong.replaceAll("、","");
            return neirong;
        }
        if(neirong.startsWith(". ")){
            neirong = neirong.replaceAll(". ","");
            return neirong;
        }
        if(neirong.startsWith("· ")){
            neirong = neirong.replaceAll("· ","");
            return neirong;
        }
        if(neirong.startsWith("1、")){
            neirong = neirong.replaceAll("1、","");
            return neirong;
        }
        if(neirong.startsWith("2、")){
            neirong = neirong.replaceAll("2、","");
            return neirong;
        }
        if(neirong.startsWith("3、")){
            neirong = neirong.replaceAll("3、","");
            return neirong;
        }
        if(neirong.startsWith("4、")){
            neirong = neirong.replaceAll("4、","");
            return neirong;
        }
        if(neirong.startsWith("5、")){
            neirong = neirong.replaceAll("5、","");
            return neirong;
        }
        if(neirong.startsWith("6、")){
            neirong = neirong.replaceAll("6、","");
            return neirong;
        }
        if(neirong.startsWith("7、")){
            neirong = neirong.replaceAll("7、","");
            return neirong;
        }
        if(neirong.startsWith("8、")){
            neirong = neirong.replaceAll("8、","");
            return neirong;
        }
        if(neirong.startsWith("9、")){
            neirong = neirong.replaceAll("9、","");
            return neirong;
        }
        if(neirong.startsWith("0、")){
            neirong = neirong.replaceAll("0、","");
            return neirong;
        }
       return neirong;
    }

    public static String isAfter(String neirong){
        if(StringUtils.isEmpty(neirong))
        {
            return neirong;
        }
        if(neirong.startsWith("1")){
            neirong = neirong.replaceAll("1","");
            return neirong;
        }
        if(neirong.startsWith("2")){
            neirong = neirong.replaceAll("2","");
            return neirong;
        }
        if(neirong.startsWith("3")){
            neirong = neirong.replaceAll("3","");
            return neirong;
        }
        if(neirong.startsWith("4")){
            neirong = neirong.replaceAll("4","");
            return neirong;
        }
        if(neirong.startsWith("5")){
            neirong = neirong.replaceAll("5、","");
            return neirong;
        }
        if(neirong.startsWith("6")){
            neirong = neirong.replaceAll("6","");
            return neirong;
        }
        if(neirong.startsWith("7")){
            neirong = neirong.replaceAll("7","");
            return neirong;
        }
        if(neirong.startsWith("8")){
            neirong = neirong.replaceAll("8","");
            return neirong;
        }
        if(neirong.startsWith("9")){
            neirong = neirong.replaceAll("9","");
            return neirong;
        }
        if(neirong.startsWith("0")){
            neirong = neirong.replaceAll("0","");
            return neirong;
        }
        return neirong;
    }
}
