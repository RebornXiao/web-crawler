package com.pachong.pachongdemo.utils;

import javax.validation.constraints.Max;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GetFoldFileNames {

    public static List<String> getFileName() {
        List<String> list = new ArrayList();
        String path = "D:/图片"; // 路径
        File f = new File(path);
        if (!f.exists()) {
            System.out.println(path + " not exists");
            return null;
        }

        File fa[] = f.listFiles();
        for (int i = 0; i < fa.length; i++) {
            File fs = fa[i];
            if (fs.isDirectory()) {
                System.out.println(fs.getName() + " [目录]");
                list.add(fs.getName());
            } else {
                System.out.println(fs.getName());
            }
        }
        return list;
    }

    public static List<String> getFileList(String urlPath) {
        List<String> list = new ArrayList();
        String path = "D:/图片" + "/" + urlPath; // 路径
        File f = new File(path);
        if (!f.exists()) {
            System.out.println(path + " not exists");
            return null;
        }

        File fa[] = f.listFiles();
        for (int i = 0; i < fa.length; i++) {
            File fs = fa[i];
            if (fs.isDirectory()) {
//                System.out.println(fs.getName() + " [目录]");
            } else {
//                System.out.println(fs.getName());
                String addUrl = fs.getName().substring(0, 5);
                if (!list.contains(addUrl)) {
                    list.add(addUrl);
                }
            }
        }
        return list;
    }
}
