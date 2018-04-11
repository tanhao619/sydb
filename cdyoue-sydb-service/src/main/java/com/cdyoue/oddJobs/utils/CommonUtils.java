package com.cdyoue.oddJobs.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

/**
 * Created by Tangguang on 2016/9/27.
 */
public class CommonUtils {
    private static final Logger logger = LoggerFactory.getLogger(CommonUtils.class);
    public static String getUUIDstr()
    {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        str = str.replaceAll("[-]","");
        return str.toUpperCase();

    }

    /**
     * 区分运行环境 返回下载资源映射路径
     * @param url 文件全路径
     * @param server 文件服务器路径
     * @param driver 文件服务器映射路径
     * @return
     */
    public static String runtimeEnvironmentGetPath(String url,String server,String driver){
        String className = Thread.currentThread().getStackTrace()[1].getClassName();
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.info("CLASSNAME:{}\n \tMETHODNAME:{}\n \t",className,methodName);
        logger.info("区分运行环境 返回下载资源映射路径,URL:{}，\n \tSERVER:{},\n \tDRIVER:{}\n \t",url,server,driver);
        String s = null;
        String strReplace = null;
        String newPath = null;
        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith("win")) {
            s = "\\\\";
            strReplace = url.replace(server, driver);
            newPath = strReplace.replaceAll("/", s);
        } else {
            newPath = url.replace(server, driver);
        }
        logger.info("newPath:{}",newPath);
        return newPath;
    }

    /**
     * 根据文件全路径返回文件类型（后缀名）
     * @param path
     * @return
     */
    public static String getFileType(String path){
        String className = Thread.currentThread().getStackTrace()[1].getClassName();
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.info("CLASSNAME:{}/METHODNAME:{}",className,methodName);
        logger.info("根据文件全路径返回文件类型（后缀名）PATH:{}",path);
        int index = path.lastIndexOf(".");
        int length = path.length();
        String type = path.substring(index, length);
        logger.info("type:{}",type);
        return  type;
    }

    /**
     * 将List<String> 转换为String 数组
     * @param str
     * @return
     */
    public static String[] getStringListGetStringArray(List<String> str){
        String[] strArr = new String[str.size()];
        str.toArray(strArr);
        return strArr;
    }

    /**
     * @Description:把数组转换为一个用逗号分隔的字符串 ，以便于用in+String 查询
     */
    public static String converToString(String[] ig) {
        String str = "";
        if (ig != null && ig.length > 0) {
            for (int i = 0; i < ig.length; i++) {
                str += ig[i] + ",";
            }
        }
        str = str.substring(0, str.length() - 1);
        return str;
    }

    /**
     * @Description:把list转换为一个用逗号分隔的字符串
     */
    public static String listToString(List list) {
        StringBuilder sb = new StringBuilder();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (i < list.size() - 1) {
                    sb.append(list.get(i) + ",");
                } else {
                    sb.append(list.get(i));
                }
            }
        }
        return sb.toString();
    }

    /**
     *
     * @param filepath  文件路径
     * @param reStr 需要替换的字符串
     * @param beReStr
     * @return
     */
    public static String getReplacePath(String filepath,String reStr,String beReStr){
        String newPath = filepath.replaceAll(reStr,beReStr);
        return newPath;
    }


    /**
     *  根据文件路径判断文件大小
     * @param filePath
     * @param ip
     * @param url
     * @return
     */
    public static long getFileSize(String filePath,String ip,String url){
        String a = runtimeEnvironmentGetPath(filePath,ip,url);
        File b = new File(a);
        long size =0;
        if (b.exists() && b.isFile()){
             size = b.length();
             logger.info("file.size:{}",size);
        }else{
            logger.error("不是文件格式！");
        }
        return size;
    }

    /**
     * 将Timestamp 转为 String
     * @param timestamp
     * @param reg
     * @return
     */
    public static String getDateString(Timestamp timestamp,String reg){
        String tsStr = "";
        SimpleDateFormat sdf = new SimpleDateFormat(reg);
        try {
            //方法一
            tsStr = sdf.format(timestamp);
            System.out.println(tsStr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tsStr;
    }
}
