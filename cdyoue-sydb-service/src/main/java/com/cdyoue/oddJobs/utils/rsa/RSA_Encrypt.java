package com.cdyoue.oddJobs.utils.rsa;

import com.cdyoue.oddJobs.exception.CustomException;
import com.cdyoue.oddJobs.exception.InvalidTokenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.security.*;

/**
 * Created by liaoyoule on 2017/5/7.
 */
@Component
public class RSA_Encrypt {
    /**
     * 指定加密算法为DESede
     */
    private static String ALGORITHM = "RSA";

    @Autowired
    private RSA_Keypaire RSA_Keypaire;
    /**
     * 加密方法 source： 源数据
     */
    public  String getToken(String source) {
        /** 将文件中的公钥对象读出 */

        Key publicKey = RSA_Keypaire.getPublicKey();
        byte[] b1 = new byte[0];
        BASE64Encoder encoder = null;

        try {
            /** 得到Cipher对象来实现对源数据的RSA加密 */
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] b = source.getBytes();
            /** 执行加密操作 */
            b1 = cipher.doFinal(b);
            encoder = new BASE64Encoder();
        } catch (Exception e) {
            throw new CustomException("获取秘钥错误");
        }
        return encoder.encode(b1);
    }

    /**
     * 解密算法 cryptograph:密文
     */
    public  String decrypt(String cryptograph)  {
        /** 将文件中的私钥对象读出 */
        Key privateKey = RSA_Keypaire.getPrivateKey();
        byte[] b = new byte[0];
        try {
            /** 得到Cipher对象对已用公钥加密的数据进行RSA解密 */
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] b1 = decoder.decodeBuffer(cryptograph);
            /** 执行解密操作 */
            b = cipher.doFinal(b1);
        } catch (Exception e) {
            e.printStackTrace();
            throw new InvalidTokenException("秘钥不合法");
        }
        return new String(b);
    }

    public static void main(String[] args) throws Exception {








        ObjectInputStream oispu = null;
        ObjectInputStream oispr = null;

        try {
            oispu = new ObjectInputStream(new FileInputStream("PublicKey"));
            oispr = new ObjectInputStream(new FileInputStream("PrivateKey"));

            Key pulicKey = (Key) oispu.readObject();
            Key privateKey = (Key) oispr.readObject();


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            if(oispu !=null){
                try {
                    oispu.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            if(oispr !=null){
                try {
                    oispr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }



        RSA_Encrypt encrypt = new RSA_Encrypt();
        String source = "408648486@qq.com";// 要加密的字符串
        String cryptograph = encrypt.getToken(source);// 生成的密文
        System.err.println(cryptograph);
//        System.out.println(cryptograph);
        System.out.println("=====================");
        String target = encrypt.decrypt(cryptograph);// 解密密文
        System.out.println(target);
    }
}
