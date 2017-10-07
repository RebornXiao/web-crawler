package com.pachong.pachongdemo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImgUtils {
    public static void getImgData(String url) {
//        String url = "https://t1.onvshen.com:85/gallery/21991/23359/001.jpg";

        for (int i = 1; i < 100; i++) {
            StringBuilder sb = new StringBuilder(url);
            if (i < 10) {
                sb.replace(url.length() - 5, url.length() - 4, i + "");
//                System.out.println(sb.toString());
            } else {
                sb.replace(url.length() - 6, url.length() - 4, i + "");
//                System.out.println(sb.toString());
            }
            String urlDowm = sb.toString();
            String name = urlDowm.substring(url.length() - 19, urlDowm.length()).replace("/", "");
            String filename = name.substring(0, 5);
//            String filename2 = name.substring(5, 10);
//            int fileName = Integer.parseInt(filename);
//            int fileName2 = Integer.parseInt(filename2);
            String imgName = name.substring(5, name.length());
//            System.out.println("名字:" + name + ";filename=" + filename + ";imgName=" + imgName);
            try {
                if(!download(urlDowm, imgName, "D:\\图片" + "\\" + filename)){
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }



    /**
     * 根据路径 下载图片 然后 保存到对应的目录下
     *
     * @param urlString
     * @param filename
     * @param savePath
     * @return
     * @throws Exception
     */
    public static boolean download(String urlString, String filename, String savePath) {
        HttpURLConnection con = null;
        // 构造URL
        try {
            URL url = new URL(urlString);
            // 打开连接
            con = (HttpURLConnection) url.openConnection();
            //设置请求的路径
            con.setConnectTimeout(10 * 1000);
            int code = con.getResponseCode();
            if (!String.valueOf(code).startsWith("2")) {
                System.out.println("下载失败----------"+urlString);
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        try {
            // 输入流
            InputStream is = con.getInputStream();
            // 1K的数据缓冲
            byte[] bs = new byte[1024];
            // 读取到的数据长度
            int len;
            // 输出的文件流
            File sf = new File(savePath);
            if (!sf.exists()) {
                sf.mkdirs();
            }
            OutputStream os = new FileOutputStream(sf.getPath() + "\\" + filename);
            System.out.println("下载成功------"+urlString);
            // 开始读取
            while ((len = is.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
            // 完毕，关闭所有链接
            os.close();

            is.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }
}
