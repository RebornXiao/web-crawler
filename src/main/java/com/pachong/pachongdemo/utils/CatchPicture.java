//package com.pachong.pachongdemo;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.OutputStream;
//import java.net.URL;
//import java.net.URLConnection;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.List;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//import com.sun.xml.internal.fastinfoset.stax.events.Util;
//
//public class CatchPicture {
//
//    /**
//     * @param args
//     */
//    public static void main(String[] args) {
//        // TODO Auto-generated method stub
//        //定义抓取图片的 正则表达式
//        String regular="[*]<b>.*?</b><br/><img src=\"(.*?)\" border=0 alt=\'(.*?)\' style=\".*?\" class=\".*?\">";
//        List<Picture> list=new CatchPicture().lookWeiboPic("http://gaoxiao.jokeji.cn/GrapHtml/dongtai/20120921221658.htm","GBK",regular,"2,1");
//        System.out.println(list.size());
//    }
//    //根据URL查看网站上的图片
//    public List<Picture> lookWeiboPic(String url,String charset,String regular,String attIndex){
//        List<Picture> list=new ArrayList<Picture>();
//        try {
//            //获取填写的url
//            //判断所属网站 获取 正则表达式
//            //获取图片存放到 list集合
//            if(!Util.isEmptyString(url)){
//                String htmls = getPageSource(url.trim(),charset);
//                Pattern pattern =null;
//                pattern = Pattern.compile(regular.trim());
//                if(!Util.isEmptyString(htmls)){
//                    Matcher matcher = pattern.matcher(htmls);
//
//                    //得到参数属性顺序
//                    String[] sort = regular.trim().split(","); //下标：0 表示 标题title ， 1 表示 图片路径
//                    //判断后缀后 得到网站的请求头部 http://www.moonbasa.com/p-032111106.html-->得到 http://www.moonbasa.com
//                    String[] suffix;
//                    suffix =url.trim().split("cn");
//                    String httphread = "";
//                    if (suffix.length > 1) {
//                        httphread = suffix[0] + "cn";
//
//                    } else {
//                        suffix = url.trim().split("com");
//                        httphread = suffix[0] + "com";
//                    }
//                    //循环匹配找到的
//                    while(matcher.find()){
//                        Picture picture=new Picture();
//
//                        //匹配出title
//                        if (-1 == Integer.parseInt(sort[0])) {
//                            // 页面上抓不到标题
//                            picture.setTitle("");
//                        } else {
//                            // 去标题的#
//                            String title=matcher.group(Integer.parseInt(sort[0])).replace("#", " ");
//                            picture.setTitle(title);
//                        }
//
//                        //匹配出source
//                        if (-1 == Integer.parseInt(sort[1])) {
//                            // 页面上抓不到图片路径
//                            picture.setSource("");
//                        }else{
//                            String webImgUrl=matcher.group(Integer.parseInt(sort[1]));
//                            //判断是绝对路径还是相对路径
//                            String[] pathType=webImgUrl.split(":");
//                            if(pathType.length>1){
//                                //绝对路径
//                                picture.setSource(webImgUrl);
//                            }else{
//                                //判断相对路径是否含有..
//                                pathType=webImgUrl.split("\\.\\.");
//                                if(pathType.length>1){
//                                    picture.setSource(httphread+pathType[1]);
//                                }else{
//                                    if(webImgUrl.startsWith("/")){
//                                        picture.setSource(httphread+pathType[0]);
//                                    }else{
//                                        picture.setSource(httphread+"/"+pathType[0]);
//                                    }
//                                }
//                            }
//                        }
//                        String upPath=upload(picture.getSource(),"d:\\image\\");
//                        picture.setUpPath(upPath);
//                        list.add(picture);
//                    }//--end while
//                }
//
//            }
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
//        return list;
//    }
//
//    /**
//     * 根据网路路径获取 页面源码
//     * @param pageUrl
//     * @param encoding
//     * @return
//     */
//    public String getPageSource(String pageUrl,String encoding) {
//        StringBuffer sb = new StringBuffer();
//        try {
//            //构建一URL对象
//            URL url = new URL(pageUrl);
//            //使用openStream得到一输入流并由此构造一个BufferedReader对象
//            BufferedReader in = new BufferedReader(new InputStreamReader(url
//                    .openStream(), encoding));
//            String line;
//            //读取www资源
//            while ((line = in.readLine()) != null) {
//                sb.append(line);
//                sb.append("\n");
//            }
//            in.close();
//        } catch (Exception ex) {
//            System.err.println(ex);
//        }
//        return sb.toString();
//    }
//
//    /**
//     * 上传 图片
//     * @param urlStr
//     * @param path
//     * @return
//     * @throws Exception
//     */
//    public String upload(String urlStr,String path) throws Exception{
//        Calendar calendar = Calendar.getInstance();
//        String month = calendar.get(Calendar.YEAR) + "/"
//                + (calendar.get(Calendar.MONTH) + 1);
//        String filename = java.util.UUID.randomUUID().toString()
//                + getExtension(urlStr);
//        path =path + month + "/";
//        download(urlStr,path,filename);
//        return path+month + "/" + filename;
//    }
////    /**
////     * 根据路径 下载图片 然后 保存到对应的目录下
////     * @param urlString
////     * @param filename
////     * @param savePath
////     * @return
////     * @throws Exception
////     */
////    public void download(String urlString, String filename,String savePath) throws Exception {
////        // 构造URL
////        URL url = new URL(urlString);
////        // 打开连接
////        URLConnection con = url.openConnection();
////        //设置请求的路径
////        con.setConnectTimeout(5*1000);
////        // 输入流
////        InputStream is = con.getInputStream();
////
////        // 1K的数据缓冲
////        byte[] bs = new byte[1024];
////        // 读取到的数据长度
////        int len;
////        // 输出的文件流
////        File sf=new File(savePath);
////        if(!sf.exists()){
////            sf.mkdirs();
////        }
////        OutputStream os = new FileOutputStream(sf.getPath()+"\\"+filename);
////        // 开始读取
////        while ((len = is.read(bs)) != -1) {
////            os.write(bs, 0, len);
////        }
////        // 完毕，关闭所有链接
////        os.close();
////
////        is.close();
////    }
////
////    /**
////     * 根据文件名 获取文件的后缀名
////     * @param fileUrl
////     * @return
////     */
////    public String getExtension(String fileUrl){
////        return fileUrl.substring(fileUrl.lastIndexOf("."), fileUrl.length());
////    }
//}