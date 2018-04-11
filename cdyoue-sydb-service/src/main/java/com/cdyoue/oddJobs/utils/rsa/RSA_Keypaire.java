package com.cdyoue.oddJobs.utils.rsa;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.Key;

/**
 * Created by liaoyoule on 2017/5/7.
 */
@Component
public class RSA_Keypaire {
    private Key publicKey;
    private Key privateKey;


    public Key getPublicKey() {
        return publicKey;
    }

    public Key getPrivateKey() {
        return privateKey;
    }

    @PostConstruct
    public void  init(){
        ObjectInputStream oispu = null;
        ObjectInputStream oispr = null;

        try {

            ResourceLoader loader = new DefaultResourceLoader();

            Resource rPu = loader.getResource("classpath:keystore/PublicKey");
            Resource rPr = loader.getResource("classpath:keystore/PrivateKey");

            oispu = new ObjectInputStream(rPu.getInputStream());
            oispr = new ObjectInputStream(rPr.getInputStream());

            Key pulicKey = (Key) oispu.readObject();
            Key privateKey = (Key) oispr.readObject();

            this.privateKey = privateKey;
            this.publicKey = pulicKey;
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
    }


}
