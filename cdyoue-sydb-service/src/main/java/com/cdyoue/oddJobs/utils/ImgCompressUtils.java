package com.cdyoue.oddJobs.utils;


import net.coobird.thumbnailator.Thumbnails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Date;

/**
 * Created by Tangguang on 2017/6/13.
 */
@Component
public class ImgCompressUtils {

    private static final Logger logger = LoggerFactory.getLogger(ImgCompressUtils.class);

    private static String SUFFIX = "mobile";



    /**
     * @param path   原始路径
     * @param h      高度
     * @param w      宽度
     * @param driver
     * @param server
     * @return
     * @throws Exception
     */
    @Async
    public void getMinPath(String path, int h, int w, String driver, String server, String compress) throws Exception {
        String url = CommonUtils.runtimeEnvironmentGetPath(path, server, driver);//根据运行环境 进行匹配文件路径
        File file = new File(url);
        if (file != null && file.length() / 1024 < 0.5) {
            File file1 = new File(file.getPath());
            if (file1.getParentFile().exists()) {
                file1.mkdirs();
            }
            FileOutputStream fout = new FileOutputStream(file1);
            fout.close();
        }

        logger.info("压缩图片开始异步执行:", new Date().toLocaleString());

        logger.info("开始读取图片资源");
        ImgCompress imgCom = new ImgCompress(url);
        logger.info("读取图片成功/width:{},height:{}", imgCom.width, imgCom.height);
        width:
        if (h == 0) {
            if (imgCom.width > 2000) {
                h = (int) (imgCom.height * 0.3);
                w = (int) (imgCom.width * 0.3);
                break width;
            } else if (imgCom.width > 1000) {
                h = (int) (imgCom.height * 0.5);
                w = (int) (imgCom.width * 0.5);
                break width;
            } else if (imgCom.width > 500) {
                h = (int) (imgCom.height * 0.7);
                w = (int) (imgCom.width * 0.7);
                break width;
            } else if (imgCom.width < 500) {
                h = (int) (imgCom.height * 0.9);
                w = (int) (imgCom.width * 0.9);
                break width;
            }
        }

        //更改压缩路径生成
//        String mobile = getSuffixPath(path, compress);//生成压缩后的路径
        String mobile = getSuffFix(path, compress);
//        mobile = CommonUtils.runtimeEnvironmentGetPath(mobile, server, driver);
//        imgCom.resizeOriginal(w, h, mobile);//按照输入比例进行压缩
        try {
            logger.info("开始压缩图片：width:{},height:{}", w, h);

            Thumbnails.of(url).size(w, h).toFile(mobile);
            logger.info("压缩图片结束：" + new Date().toLocaleString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("结束：" + new Date().toLocaleString());

    }

    /**
     * @param path   原始路径
     * @param h      高度
     * @param w      宽度
     * @param driver
     * @param server
     * @return
     * @throws Exception
     */
    @Async
    public void getMinPath(File file, String path, int h, int w, String driver, String server, String compress) throws Exception {
        if (file != null && file.length() / 1024 < 0.5) {
            File file1 = new File(file.getPath());
            if (file1.getParentFile().exists()) {
                file1.mkdirs();
            }
            FileOutputStream fout = new FileOutputStream(file1);
            fout.close();
        }
        logger.info("压缩图片开始异步执行:", new Date().toLocaleString());
        String url = CommonUtils.runtimeEnvironmentGetPath(path, server, driver);//根据运行环境 进行匹配文件路径
        //更改压缩路径生成
//        String mobile = getSuffixPath(path, compress);//生成压缩后的路径
        String mobile = getSuffFix(url, compress);//生成压缩后的路径
        logger.info("开始读取图片资源");
//        ImgCompress imgCompress = new ImgCompress(file);
        BufferedImage read = ImageIO.read(file);//读取图片
        int width = read.getWidth(null);//获取宽度
        int height = read.getHeight(null);//获取高度
//        int width = imgCompress.width;//获取宽度
//        int height = imgCompress.height;//获取高度
//        w = width;
//        h = height;
        logger.info("读取图片成功/width:{},height:{}", width, height);
        /**
         * 根据图片高度设置高宽
         */
        i:
        if (h == 0) {
            if (width > 2000) {
                h = (int) (height * 0.3);
                w = (int) (width * 0.3);
                break i;
            } else if (width > 1000) {
                h = (int) (height * 0.5);
                w = (int) (width * 0.5);
                break i;
            } else if (width > 500) {
                h = (int) (height * 0.7);
                w = (int) (width * 0.7);
                break i;
            } else if (width < 500) {
                h = (int) (height * 0.8);
                w = (int) (width * 0.8);
                break i;
            }
        }

        /**
         * 选择压缩方式
         */
//        imgCom.resizeOriginal(w, h, mobile);//按照输入比例进行压缩
        try {
            logger.info("开始压缩图片：width:{},height:{}", w, h);
//            logger.info("开始压缩图片：width:{},height:{}",width,height);
            if (file.getParentFile().exists()) file.getParentFile().mkdirs();
//            imgCompress.resizeOriginal(w, h, mobile);//按照输入比例进行压缩
            Thumbnails.of(file).size(w, h).toFile(mobile);//压缩图片
            logger.info("压缩图片结束：" + new Date().toLocaleString());
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("压缩图片失败：", e.getMessage());
        }
    }


    /**
     * @param normalPath 原始路径
     * @param suffix     后缀（添加符）
     * @return
     */
    public static String getSuffixPath(String normalPath, String suffix) {
        logger.info("normalPath{}{},\n \tsyffix:{}", normalPath, suffix);
        String runTimeStr = getRunTimeStr();
        String reg = "";
        if (runTimeStr.toLowerCase().startsWith("win")) {
            reg = "\\\\";
        } else {
            reg = "/";
        }
        String[] arr = normalPath.split(reg);
        if (arr.length > 1) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < arr.length; i++) {
                if (i == arr.length - 1) {
                    sb.append("" + suffix + "/" + arr[i]);
                } else {
                    sb.append(arr[i] + "/");
                }
            }
            logger.info("path：{}", sb.toString());
            return sb.toString();
        } else {
            logger.info("path:{}", normalPath);
            return normalPath;
        }
    }

    /**
     * @param normalPath
     * @param suffix
     * @return
     */
    public static String getSuffFix(String normalPath, String suffix) {
        String runTimeStr = getRunTimeStr();
        String reg = "";
        if (runTimeStr.toLowerCase().startsWith("win")) {
            reg = "\\\\";
        } else {
            reg = "/";
        }
        String[] arr = normalPath.split(reg);
        if (arr.length > 1) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < arr.length; i++) {
                if (i == arr.length - 1) {
                    String[] arry = arr[i].split("\\.");
                    if (arry.length > 1) {
                        sb.append(arry[0]).append("_" + suffix + ".").append(arry[1]);
                    }
                } else {
                    sb.append(arr[i] + "/");
                }
            }
            logger.info("path：{}", sb.toString());
            return sb.toString();
        } else {
            logger.info("path:{}", normalPath);
            return normalPath;
        }

    }

    private static String getRunTimeStr() {
        String os = System.getProperty("os.name");
        logger.info("运行环境：{}", os);
        return os;
    }

    public static void main(String[] args) {
        String str = "Z:/lgsq/.PNG/捕获_20170428_c4bfa470-2353-4867-8590-654da5b593f7.PNG";
        System.out.println(CommonUtils.runtimeEnvironmentGetPath(str, "Z:/", "http://172.16.0.3:8081/"));
    }



    @Async
    public void compress(File file, File dest) throws IOException {
        BufferedImage image = ImageIO.read(file);
        DecimalFormat df = new DecimalFormat("0.0");
        int width = image.getWidth();
        int height = image.getHeight();
        Double wScale = Double.parseDouble(df.format(360D / width));
        Double hScale = Double.parseDouble(df.format(600D / height));
        Double scale = wScale > hScale ? wScale : hScale;
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }

        Thumbnails.of(file)
                .scale(scale)
                .toFile(dest);
    }


}
