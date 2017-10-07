package com.pachong.pachongdemo;


public class DownImgTask implements Runnable {
    private String urlDowm;

    public DownImgTask(String urlDowm) {
        this.urlDowm = urlDowm;
    }

    @Override
    public void run() {
        ImgUtils.getImgData(urlDowm);
    }
}
