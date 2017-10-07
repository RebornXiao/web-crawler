package com.pachong.pachongdemo;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/*
 * 网络爬虫取数据
 *
 * */
public class PachongDemoApplication {
    public static String GetUrl(String inUrl) {
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(inUrl);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

            String temp = "";
            while ((temp = reader.readLine()) != null) {
                //System.out.println(temp);
                sb.append(temp);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static List<String> GetMatcher(String str, String url) {
        List<String> result = new ArrayList<String>();
        Pattern p = Pattern.compile(url);//获取网页地址
        Matcher m = p.matcher(str);
        while (m.find()) {
            //System.out.println(m.group(1));
            result.add(m.group(1));
        }
        return result;
    }

    public static void getImg() {
        String str = GetUrl("http://www.163.com");
        List<String> ouput = GetMatcher(str, "src=\"([\\w\\s./:]+?)\"");

        for (String temp : ouput) {
            //System.out.println(ouput.get(0));
            System.out.println(temp);
        }
        String aurl = ouput.get(0);
        // 构造URL
        URL url;
        try {
            url = new URL(aurl);
            // 打开URL连接
            URLConnection con = (URLConnection) url.openConnection();
            // 得到URL的输入流
            InputStream input = con.getInputStream();
            // 设置数据缓冲
            byte[] bs = new byte[1024 * 2];
            // 读取到的数据长度
            int len;
            // 输出的文件流保存图片至本地
            OutputStream os = new FileOutputStream("a.png");
            while ((len = input.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
            os.close();
            input.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String args[]) {

    }



    /**
     * 根据文件名 获取文件的后缀名
     *
     * @param fileUrl
     * @return
     */
    public static String getExtension(String fileUrl) {
        return fileUrl.substring(fileUrl.lastIndexOf("."), fileUrl.length());
    }


}