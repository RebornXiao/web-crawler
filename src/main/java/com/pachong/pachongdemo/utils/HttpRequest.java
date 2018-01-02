package com.pachong.pachongdemo.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HttpRequest {
    public static String sendGet(String url) {
        return new String(getContent(url));
    }



    public static byte[] getContent(String url)  {
        DefaultHttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(url);
        byte[] bytes = null;
        try {
            HttpResponse response = client.execute(get);
            HttpEntity entity = response.getEntity();
            bytes = EntityUtils.toByteArray(entity);
        }catch (IOException e){
            e.printStackTrace();
        }
        String content = new String(bytes);
        // 默认为utf-8编码
        String charset = "utf-8";
        // 匹配<head></head>之间，出现在<meta>标签中的字符编码
        Pattern pattern = Pattern.compile("<head>([\\s\\S]*?)<meta([\\s\\S]*?)charset\\s*=(\")?(.*?)\"");
        Matcher matcher = pattern.matcher(content.toLowerCase());
        if (matcher.find()) {
            charset = matcher.group(4);
            if (charset.equals("gb2312")) {
                try{
                byte[] gbkBytes = new String(bytes, "gbk").getBytes();
                return new String(gbkBytes, "utf-8").getBytes();}
                catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        // 将目标字符编码转化为utf-8编码
        try {
            String temp = new String(bytes, charset);
            byte[] contentData = temp.getBytes("utf-8");
            return contentData;
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url   发送请求的URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        String urlNameString = url;
        if (param != null && !param.equals("")) {
            urlNameString = url + "?" + param;
        }
        try {

            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            if (!String.valueOf(connection.getResponseCode()).startsWith("2")) {
                return null;
            }
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
//            for (String key : map.keySet()) {
//                System.out.println(key + "--->" + map.get(key));
//            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }

        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        String content = new String(result);
        byte[] resultByte = result.getBytes();
        // 默认为utf-8编码
        String charset = "utf-8";
        // 匹配<head></head>之间，出现在<meta>标签中的字符编码
        Pattern pattern = Pattern.compile("<head>([\\s\\S]*?)<meta([\\s\\S]*?)charset\\s*=(\")?(.*?)\"");
        Matcher matcher = pattern.matcher(content.toLowerCase());
        if (matcher.find()) {
            charset = matcher.group(4);
            if (charset.equals("gb2312")) {
                try {
                    byte[] gbkBytes = new String(resultByte, "gbk").getBytes();
                    return new String(gbkBytes, "utf-8");
                }catch (Exception e){
                    return "";
                }

            }
        }
        // 将目标字符编码转化为utf-8编码
        try {
            String temp = new String(resultByte, charset);
            byte[] contentData = temp.getBytes("utf-8");
            return new String(contentData);
        }catch (Exception e){
            return  "";
        }
    }


    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url   发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

//    public static void main(String[] args) {
////        String url = "http://www.zngirls.com/ajax/click_update_handler.ashx";
////        String url = "https://www.nvshens.com/ajax/click_update_handler.ashx";
//        String url = "https://www.nvshens.com/gallery/";//?name=推女郎";
////        String url = "https://www.nvshens.com/girl/search.aspx";//?name=推女郎";
//        //发送 GET 请求
//        String s = HttpRequest.sendGet(url, "name=推女郎");
//        //发送 POST 请求
////        String sr = HttpRequest.sendPost(url, null);
////        String s = HttpRequest.sendPost(url, "name=推女郎");
//        System.out.println(s);
////        getParByString(s);
//        getImgUrlByString(s);
//
//    }

    public static List<String> getFenlei() {
        String url = "https://www.nvshens.com/gallery/";//?name=推女郎";
        String s = HttpRequest.sendGet(url, null);
        return getParByString(s);
    }

    public static List<String> getFenleiImg(String fenlei) {
        String url = "https://www.nvshens.com" + fenlei;//?name=推女郎";
        List<String> urlList = new ArrayList();
        for (int i = 0; i < 50; i++) {
            if (i > 0) {
                url = "https://www.nvshens.com" + fenlei + ("/" + i + ".html");
            }
            String s = HttpRequest.sendGet(url, null);
            if (s != null && !s.equals("")
//                    &&!s.contains("该页面未找到")
                    ) {
                urlList.addAll(getImgUrlByString(s));
            } else {
                break;
            }
        }
        return urlList;

    }

    //直接从字符串中获取
    //获取分类信息
    public static List<String> getParByString(String html) {
        Document doc = Jsoup.parse(html);//解析HTML字符串返回一个Document实现
        Elements links = doc.select("a");//查找第一个a元素
        List hrefList = new ArrayList();
//        for (Element link : links) {
//            String text = doc.body().text(); // "An example link"//取得字符串中的文本
//            String linkHref = link.attr("href"); // "http://example.com/"//取得链接地址
//            String linkText = link.text(); // "example""//取得链接地址中的文本
//
//            String linkOuterH = link.outerHtml();
//            // "<a href="http://example.com"><b>example</b></a>"
//            String linkInnerH = link.html(); // "<b>example</b>"//取得链接内的html内容
//
//            if (linkHref.startsWith("/gallery") && !linkHref.endsWith("/gallery") && !linkHref.endsWith("html") && !hrefList.contains(linkHref)) {
//                System.out.println(linkHref);
//                hrefList.add(linkHref);
//            }
//
////        System.out.println(linkclass);}
//        }
        return hrefList;
    }

    //直接从字符串中获取
    //获取分类信息
    public static List<String> getImgUrlByString(String html) {
        Document doc = Jsoup.parse(html);//解析HTML字符串返回一个Document实现
        Elements links = doc.select("img");//查找第一个a元素
        List hrefList = new ArrayList();
        for (Element link : links) {
            String text = doc.body().text(); // "An example link"//取得字符串中的文本
//            String linkHref = link.attr("href"); // "http://example.com/"//取得链接地址
            String linkHref = link.attr("data-original"); // "http://example.com/"//取得链接地址
            String linkText = link.text(); // "example""//取得链接地址中的文本
            String linkOuterH = link.outerHtml();
            // "<a href="http://example.com"><b>example</b></a>"
            String linkInnerH = link.html(); // "<b>example</b>"//取得链接内的html内容
            System.out.println(linkHref);
            if (linkHref.startsWith("https") && !hrefList.contains(linkHref)) {
                System.out.println(linkHref);
                hrefList.add(linkHref);
            }
        }
        return hrefList;

    }
}
