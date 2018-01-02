package com.pachong.pachongdemo.controller;

import com.pachong.pachongdemo.model.Member;
import com.pachong.pachongdemo.service.MemberService;
import com.pachong.pachongdemo.utils.HttpRequest;
import com.pachong.pachongdemo.utils.NeirongUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@RestController
public class MemberContrller {

    private final static Logger logger = LoggerFactory.getLogger(MemberContrller.class);
    ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

    @Autowired
    MemberService memberService;

    @RequestMapping("zlm")
    public void zhengli(){
        List<Member> members = memberService.findAll();
        for (int i = 0,size = members.size(); i < size; i++) {
            logger.info("处理中：+"+i);
            Member member = members.get(i);
            String neirong = member.getNeirong();
            neirong = NeirongUtils.splitNeirong(neirong);
            if(!neirong.equals(member.getNeirong())){
                member.setNeirong(neirong);
                memberService.saveAndFlush(member);
            }
        }
    }

    @RequestMapping("wm")
    public String getData() throws  Exception{
//        String url = "http://www.mouxiao.com/jingdianmingyan/";
        String url = "http://www.mouxiao.com/lizhimingyan/45.html";
        String type = "经典名言";

//        getDa(url,type);
        getP(url);
        return type;
    }

    @RequestMapping("qm")
    public String getMingyan() throws  Exception{
//        String url = "http://www.mouxiao.com/jingdianmingyan/";
        String url = "http://www.ceasm.com/lizhimingyan/";
        for (int i = 1; i < 11; i++) {
            String  urlweb = url;
            if(i>1) {
                urlweb = url + "list" + i + ".html";
            }
            getP(urlweb);
        }
        String type = "励志名言";
        getP(url);
        return type;
    }

    void getP(String url) throws  Exception{
        String resp = HttpRequest.sendGet(url);
        Document document = Jsoup.parse(resp);//解析HTML字符串返回一个Document实现
        Elements elements = document.select("h4");
        List<String> hrefList = new ArrayList();
        for (Element link : elements) {
            Elements eles =   link.select("a");
            for (Element lin : eles) {
                String linkHref = lin.attr("href");
                hrefList.add(linkHref);
            }
        }
        for (int i =0,size = hrefList.size();i<size;i++){
            String urlGeyan = hrefList.get(i);
            String res = HttpRequest.sendGet(urlGeyan);
            Document doc = Jsoup.parse(res);//解析HTML字符串返回一个Document实现
            Elements els = doc.select("div");//查找第一个a元素
            for (Element l : els) {
                String text = l.text(); // "An example link"//取得字符串中的文本
               String text1 = gb2312eecode(text);
                String text2 = gb2312ToUtf8(text);
                //String  text3=  decodeUnicode(text2);
                if(!StringUtils.isEmpty(text)&&text.length()>15){
                    Member member = new Member();
                    member.setNeirong(text);
                    member.setType("励志名言");
                    // memberService.saveAndFlush(member);
                }
            }
        }
    }

    public  String decodeUnicode( String dataStr) {
        int start = 0;
        int end = 0;
        StringBuffer buffer = new StringBuffer();
        while (start > -1) {
            end = dataStr.indexOf("\\u", start + 2);
            String charStr = "";
            if (end == -1) {
                charStr = dataStr.substring(start + 2, dataStr.length());
            } else {
                charStr = dataStr.substring(start + 2, end);
            }
            char letter = (char) Integer.parseInt(charStr, 16); // 16进制parse整形字符串。
            buffer.append(new Character(letter).toString());
            start = end;
        }
        return buffer.toString();
    }

    public  String gb2312eecode(String string){
        StringBuffer gbkStr = new StringBuffer();
        try {
            byte[] gbkDecode = string.getBytes("gb2312");
            for (byte b : gbkDecode) {
                gbkStr.append(Integer.toHexString(b & 0xFF));
            }
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return gbkStr.toString();
    }

    public  String gb2312ToUtf8(String str) {
        String urlEncode = "" ;
        try {
            urlEncode = URLEncoder.encode (str, "UTF-8" );
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return urlEncode;
    }

    void getDa(String url,String type) throws  Exception{
        String html = HttpRequest.sendGet(url);
        Document doc = Jsoup.parse(html);//解析HTML字符串返回一个Document实现
        Elements links = doc.select("a");//查找第一个a元素
        List<String> hrefList = new ArrayList();
        for (Element link : links) {
            String text = doc.body().text(); // "An example link"//取得字符串中的文本
            String linkHref = link.attr("href"); // "http://example.com/"//取得链接地址
            String linkText = link.text(); // "example""//取得链接地址中的文本
            String linkOuterH = link.outerHtml();
            // "<a href="http://example.com"><b>example</b></a>"
            String linkInnerH = link.html(); // "<b>example</b>"//取得链接内的html内容
            if (linkHref.endsWith("html") ) {
                System.out.println(linkHref);
                hrefList.add(url+linkHref);
            }
//        System.out.println(linkclass);}
        }
        for (int i =0,size = hrefList.size();i<size;i++){
            String urlGeyan = hrefList.get(i);
            String resp = HttpRequest.sendGet(urlGeyan);
            Document document = Jsoup.parse(resp);//解析HTML字符串返回一个Document实现
            Elements elements = document.select("p");//查找第一个a元素
            for (Element link : elements) {
                String text = link.text(); // "An example link"//取得字符串中的文本
                if(!StringUtils.isEmpty(text)&&text.length()>15){
                    Member member = new Member();
                    member.setNeirong(text);
                    member.setType(type);
                    memberService.saveAndFlush(member);
                }
            }
        }
    }
}