package com.pachong.pachongdemo;

import org.apache.catalina.ssi.SSIFsize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@RestController
public class ImgContrller {

    ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

    @Autowired
    ImgService imgService;
    @Autowired
    FenleiService fenleiService;
    @Autowired
    HaveUrlService haveUrlService;
    @Autowired
    DownUrlService downUrlService;

    @RequestMapping("getimg")
    public String getImage() {
        List<ImgUrl> all = imgService.findAll();
        System.out.println(all);
        return "ok";
    }

    @RequestMapping("fenlei")
    public String getFenlei() {
        List<String> all = HttpRequest.getFenlei();
        for (String str : all) {
            ImgFenlei imgFenlei = new ImgFenlei();
            imgFenlei.setFenleiName(str);
            fenleiService.saveAndFlush(imgFenlei);
        }
        return "ok";
    }

    @RequestMapping("down")
    public String downImg() throws Exception {

        //https://t1.onvshen.com:85/gallery/24986/24022/cover/0.jpg
        //https://t1.onvshen.com:85/gallery/20763/24036/001.jpg
        List<ImgFenlei> fenleiList = fenleiService.findAll();
        List<ImgHaveUrl> haveUrls = haveUrlService.findAll();
        List<Integer> urls = new ArrayList();
        for (int i = 0, size = haveUrls.size(); i < size; i++) {
            int number = Integer.parseInt(haveUrls.get(i).getFileName());
            urls.add(number);
        }

        for (int size = fenleiList.size(), i = 0; i < 2; i++) {
            String fenleiName = fenleiList.get(i).getFenleiName();
            List<String> all = HttpRequest.getFenleiImg(fenleiName);
            for (String str : all) {
                StringBuilder sb = new StringBuilder(str);
                StringBuilder name = sb.replace(sb.length() - 11, sb.length() - 4, "001");
                String numberStr = str.substring(str.length() - 23, str.length() - 18);
                if (UrlUtils.isNumeric(numberStr)) {
                    int fileName = Integer.parseInt(numberStr);
                    String url = name.toString();
                    ImgUrl imgUrl = new ImgUrl();
                    imgUrl.setFenlei(fenleiName);
                    imgUrl.setImgUrl(url);
                    imgService.saveAndFlush(imgUrl);
                    if (!urls.contains(fileName)) {
//                    cachedThreadPool.execute(new DownImgTask(url));

                    }
                }
            }
        }
        return "ok";
    }

    @RequestMapping("have")
    public String have() {
        haveUrlService.deleteAllInBatch();
        List<String> nameList = GetFoldFileNames.getFileName();
        for (String name : nameList) {
            ImgHaveUrl imgHaveUrl = new ImgHaveUrl();
            imgHaveUrl.setFileName(name);
            haveUrlService.saveAndFlush(imgHaveUrl);

        }
        return "ok";
    }

    @RequestMapping("only")
    public String only() {
        List<ImgUrl> allImgs = imgService.findAll();
        List<String> listUrls = new ArrayList<>();
        for (int i = 0, size = allImgs.size(); i < size; i++) {
            listUrls.add(allImgs.get(i).getImgUrl());
        }
        List<String> imgUrls = UrlUtils.getSingle(listUrls);
        System.out.println("不重复的个数：" + imgUrls.size());
        for (int i = 0, size = imgUrls.size(); i < size; i++) {
            String urlPath = imgUrls.get(i);
            ImgDownUrl imgDownUrl = new ImgDownUrl();
            imgDownUrl.setUrlPath(urlPath);
            downUrlService.saveAndFlush(imgDownUrl);
        }
        return "ok";
    }

    @RequestMapping("toget")
    public String getImg() {
        List<ImgDownUrl> downUrlList = downUrlService.findAll();
        List<String> allUrls = new ArrayList<>();
        for (int i = 0, size = downUrlList.size(); i < size; i++) {
            allUrls.add(downUrlList.get(i).getUrlPath());
        }
        List<ImgHaveUrl> haveUrls = haveUrlService.findAll();
        List<Integer> urls = new ArrayList();
        for (int i = 0, size = haveUrls.size(); i < size; i++) {
            int number = Integer.parseInt(haveUrls.get(i).getFileName());
            urls.add(number);
        }
        for (int i = 0, size = allUrls.size(); i < size; i++) {
            ImgDownUrl imgDownUrl = downUrlList.get(i);
            if (imgDownUrl.getIsDown() == 0) {
                String str = allUrls.get(i);
                //String url = "https://t1.onvshen.com:85/gallery/21991/23359/001.jpg";
                String filename = str.substring(str.length() - 19, str.length() - 14);
                String filename2 = str.substring(str.length() - 13, str.length() - 8);
                if (UrlUtils.isNumeric(filename)) {
                    int fileName = Integer.parseInt(filename);
                    if (!urls.contains(fileName)) {
                        System.out.println(i + "包名不存在：" + filename + ";下载：" + str);
                        imgDownUrl.setIsDown(0);
                        downUrlService.saveAndFlush(imgDownUrl);
                        cachedThreadPool.execute(new DownImgTask(str));
                    } else {
                        List<String> fileNameList = GetFoldFileNames.getFileList(filename);
                        if (!fileNameList.contains(filename2)) {
                            System.out.println(i + "包名存在：" + filename + ";下载：" + str);
                            imgDownUrl.setIsDown(0);
                            downUrlService.saveAndFlush(imgDownUrl);
                            cachedThreadPool.execute(new DownImgTask(str));
                        } else {
                            System.out.println(i + "包名存在：" + filename + ";不下载");
                            imgDownUrl.setIsDown(1);
                            downUrlService.saveAndFlush(imgDownUrl);
                        }
                    }
                }
            }
        }
        System.out.println("完成~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~·");
        return "ok";
    }

    @RequestMapping("file")
    public String getFileName() {
        List<String> fileNameList = GetFoldFileNames.getFileList("10577");
        List<String> fileList = new ArrayList<>();


        return "ok";
    }
}