package com.cdyoue.oddJobs.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * 图片压缩处理
 */
public class ImgCompress {
    private static Logger logger = LoggerFactory.getLogger(ImgCompress.class);

    public Image img;
    public int width;
    public int height;

//    @SuppressWarnings("deprecation")
//    public static void main(String[] args) {
//        try {
//            System.out.println("开始：" + new Date().toLocaleString());
//            ImgCompress imgCom = new ImgCompress("C:\\Users\\Administrator\\Desktop\\QQ截图20170613132632.png");
//            System.out.println(imgCom.height);
//            System.out.println(imgCom.width);
//            int h = imgCom.height;
//            int w = imgCom.width;
//            imgCom.resizeFix(w, h, "F:\\12231233.png");
//            System.out.println("结束：" + new Date().toLocaleString());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }

    /**
     * 构造函数
     */
    public ImgCompress(String fileName) throws IOException {
        File file = new File(fileName);// 读入文件
        if (file.exists() && file.isFile()){
            logger.info("读入文件-------------文件名称：{}，文件大小：{}",file.getName(),file.length());
        }else {
            throw new IOException("写入文件找不到获取不是一张图片");
        }
        img = ImageIO.read(file);      // 构造Image对象
        width = img.getWidth(null);    // 得到源图宽
        height = img.getHeight(null);  // 得到源图长
    }

    /**
     * 构造函数
     */
    public ImgCompress(File fileI) throws IOException {
        File file = new File(fileI.getPath());// 读入文件
        if (file.exists() && file.isFile()) {
            logger.info("读入文件-------------文件名称：{}，文件大小：{}", file.getName(), file.length());
        } else {
            throw new IOException("写入文件找不到获取不是一张图片");
        }
        img = ImageIO.read(file);      // 构造Image对象
        width = img.getWidth(null);    // 得到源图宽
        height = img.getHeight(null);  // 得到源图长
    }

    /**
     * 按照宽度还是高度进行压缩
     *
     * @param w int 最大宽度
     * @param h int 最大高度
     */
    public void resizeFix(int w, int h, String path) throws IOException {
        if (width / height > w / h) {
            resizeByWidth(w, path);
        } else {
            resizeByHeight(h, path);
        }
    }

    /**
     * 按照原始比例 进行压缩
     */
    public void resizeOriginal(int w, int h, String path) throws IOException {
        resize(w,h,path);
    }

    /**
     * 以宽度为基准，等比例放缩图片
     *
     * @param w int 新宽度
     */
    public void resizeByWidth(int w, String path) throws IOException {
        int h = (int) (height * w / width);
        resize(w, h, path);
    }

    /**
     * 以高度为基准，等比例缩放图片
     *
     * @param h int 新高度
     */
    public void resizeByHeight(int h, String path) throws IOException {
        int w = (int) (width * h / height);
        resize(w, h, path);
    }

    /**
     * 强制压缩/放大图片到固定的大小
     *
     * @param w int 新宽度
     * @param h int 新高度
     */
    public void resize(int w, int h, String path) {
        try {
            // SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢
            BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
            image.getGraphics().drawImage(img, 0, 0, w, h, null); // 绘制缩小后的图
//            image.getGraphics().drawImage(img.getScaledInstance(w, h, Image.SCALE_SMOOTH), 0, 0, null);//SCALE_SMOOTH 速度较慢  但是质量比较好

            String newPath = path.replaceAll("/", "\\\\");
            File destFile = new File(newPath);
            System.out.println(destFile.getParentFile());
            if (!destFile.getParentFile().exists()) {
                destFile.getParentFile().mkdirs();
            }
//            FileOutputStream out = new FileOutputStream(destFile); // 输出到文件流
//            // 可以正常实现bmp、png、gif转jpg
//            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//            encoder.encode(image); // JPEG编码
//            out.close();
            /**
             * 因JDK 1.8 1.7 报错 故如此处理
             */
            String formatName = newPath.substring(newPath.lastIndexOf(".") + 1);
            ImageIO.write(image, formatName, destFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}