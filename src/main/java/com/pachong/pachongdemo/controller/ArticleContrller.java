package com.pachong.pachongdemo.controller;

import com.pachong.pachongdemo.model.Article;
import com.pachong.pachongdemo.model.Member;
import com.pachong.pachongdemo.service.ArticleService;
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
public class ArticleContrller {

    private final static Logger logger = LoggerFactory.getLogger(ArticleContrller.class);
    ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

    @Autowired
    ArticleService articleService;

    @RequestMapping("q")
    public String getMingyan() throws Exception {
//        String url = "http://www.mouxiao.com/jingdianmingyan/";
        String urlEnd = ".html";
        String url = "http://www.szwj72.cn/Article/hsyy/201712/2758.html";
        String type = "感悟生活";
        String hehe = "ok";
        hehe = getP(url);
        return hehe;
    }

    String getP(String url) {
        String resp = HttpRequest.sendGet(url);
        if (StringUtils.isEmpty(resp)) {
            return "返回为空";
        }
        Document doc = Jsoup.parse(resp);//解析HTML字符串返回一个Document实现
        Elements els = doc.getElementsByClass("article-content");
        Elements elType = doc.getElementsByClass("article-meta");
        Elements nexts = doc.getElementsByClass("list-group-item bg-warning");
        Elements elements1 = doc.select("h1");
        String title = "";
        String type = "";
        String urlNext = "";
        for (Element ls : nexts) {
            Elements elements = ls.select("a");
            for (Element element : elements) {
                urlNext = element.attr("href");
            }
        }
        for (Element ls : elements1) {
            title = ls.text();
        }
        for (Element ls : elType) {
            Elements elements = ls.select("a");
            for (Element element : elements) {
                type = element.text();
            }
        }
        for (Element l : els) {
            String text = l.text(); // "An example link"//取得字符串中的文本
            if (!StringUtils.isEmpty(text) && text.length() > 15) {
                Article article = new Article();
                article.setNeirong(text);
                article.setType(type);
                article.setTitile(title);
                if (text.length() > 20000) {
                    logger.info("这哥们写了2万字，装不下了！");
                } else {
                    articleService.saveAndFlush(article);
                }
            }
        }
        getP(urlNext);
        return "ok";
    }
}