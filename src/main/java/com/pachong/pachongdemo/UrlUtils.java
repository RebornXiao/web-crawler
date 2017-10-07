package com.pachong.pachongdemo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlUtils {

    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }

    public static List<String> getSingle(List list) {
        List tempList = new ArrayList<>();          //1,创建新集合
        Iterator it = list.iterator();              //2,根据传入的集合(老集合)获取迭代器
        while(it.hasNext()) {                  //3,遍历老集合
            Object obj = it.next();                //记录住每一个元素
            if(!tempList.contains(obj)) {            //如果新集合中不包含老集合中的元素
                tempList.add(obj);                //将该元素添加
            }
        }
        return tempList;
    }

}
