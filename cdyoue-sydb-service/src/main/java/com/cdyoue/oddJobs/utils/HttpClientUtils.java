package com.cdyoue.oddJobs.utils;

/**
 * Created by Administrator on 2017/10/27.
 */

/**
 * Created by Administrator on 2017/6/27.
 */

        import java.io.File;
        import java.io.IOException;
        import java.io.UnsupportedEncodingException;
        import java.util.ArrayList;
        import java.util.List;


        import com.alibaba.fastjson.JSONObject;
        import org.apache.http.HttpEntity;
        import org.apache.http.ParseException;
        import org.apache.http.client.ClientProtocolException;

        import org.apache.http.client.entity.UrlEncodedFormEntity;
        import org.apache.http.client.methods.CloseableHttpResponse;
        import org.apache.http.client.methods.HttpGet;
        import org.apache.http.client.methods.HttpPost;
        import org.apache.http.entity.ContentType;
        import org.apache.http.entity.mime.MultipartEntityBuilder;
        import org.apache.http.entity.mime.content.FileBody;
        import org.apache.http.entity.mime.content.StringBody;
        import org.apache.http.impl.client.CloseableHttpClient;
        import org.apache.http.impl.client.HttpClients;
        import org.apache.http.message.BasicNameValuePair;
        import org.apache.http.util.EntityUtils;

public class HttpClientUtils {

    /**
     * post方式提交
     */
    public static String postForm(String url,List<BasicNameValuePair> formparams) {
        String body = null;
        // 创建默认的httpClient实例.
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建httppost
        HttpPost httppost = new HttpPost(url);
        UrlEncodedFormEntity uefEntity;
        try {
            uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");

            httppost.setEntity(uefEntity);

            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    System.out.println("Response content: " + EntityUtils.toString(entity, "UTF-8"));
                    body = EntityUtils.toString(entity, "UTF-8");
                }
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return body;
    }

    /**
     * 发送 post请求访问本地应用并根据传递参数不同返回不同结果
     */
//    public static String doPost(String url,List<BasicNameValuePair> formparams) {
//        // 创建默认的httpClient实例.
//        String postBody = null;
//        CloseableHttpClient httpclient = HttpClients.createDefault();
//        // 创建httppost
//        HttpPost httppost = new HttpPost(url);
//        // 创建参数队列
//        UrlEncodedFormEntity uefEntity;
//        try {
//            uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
//            httppost.setEntity(uefEntity);
//            CloseableHttpResponse response = httpclient.execute(httppost);
//            try {
//                HttpEntity entity = response.getEntity();
//                if (entity != null) {
//                    postBody = EntityUtils.toString(entity, "UTF-8");
//                }
//            } finally {
//                response.close();
//            }
//        } catch (ClientProtocolException e) {
//            e.printStackTrace();
//        } catch (UnsupportedEncodingException e1) {
//            e1.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            // 关闭连接,释放资源
//            try {
//                httpclient.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return postBody;
//    }

    public static String doPost(String url,List<BasicNameValuePair> formparams) throws Exception{
        // 创建默认的httpClient实例.
        String postBody = null;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建httppost
        HttpPost httppost = new HttpPost(url);
        // 创建参数队列
        UrlEncodedFormEntity uefEntity;
            uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
            httppost.setEntity(uefEntity);
            CloseableHttpResponse response = httpclient.execute(httppost);

                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    postBody = EntityUtils.toString(entity, "UTF-8");
                }

                response.close();

            // 关闭连接,释放资源

                httpclient.close();


        return postBody;
    }

    /**
     * 发送 get请求
     */
    public static String doGet(String url) {
        String getBody = null;
        CloseableHttpClient httpclient = HttpClients.createDefault();
  /*      RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setSocketTimeout(5000)
                .setConnectTimeout(5000)
                .setConnectionRequestTimeout(5000)
                .build();
        CloseableHttpClient httpclient = HttpClients.custom()
                .setDefaultRequestConfig(defaultRequestConfig)
                .build();*/
        try {
            // 创建httpget.
            HttpGet httpget = new HttpGet(url);
            // 执行get请求.
            CloseableHttpResponse response = httpclient.execute(httpget);
            try {
                // 获取响应实体
                HttpEntity entity = response.getEntity();
                //getBody = EntityUtils.toString(entity,"UTF-8");
                getBody = EntityUtils.toString(entity,"UTF-8");
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return getBody;
    }

    /**
     * 上传文件
     */
    public void upload(String url,String filePath) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpPost httppost = new HttpPost(url);

            FileBody bin = new FileBody(new File(filePath));
            StringBody comment = new StringBody("A binary file of some kind", ContentType.TEXT_PLAIN);

            HttpEntity reqEntity = MultipartEntityBuilder.create().addPart("bin", bin).addPart("comment", comment).build();

            httppost.setEntity(reqEntity);

            System.out.println("executing request " + httppost.getRequestLine());
            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                System.out.println("----------------------------------------");
                System.out.println(response.getStatusLine());
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    System.out.println("Response content length: " + resEntity.getContentLength());
                }
                EntityUtils.consume(resEntity);
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * HttpClient连接SSL
     */
    /*public static void ssl() {
        CloseableHttpClient httpclient = null;
        try {
            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            FileInputStream instream = new FileInputStream(new File("d:\\tomcat.keystore"));
            try {
                // 加载keyStore d:\\tomcat.keystore
                trustStore.load(instream, "123456".toCharArray());
            } catch (CertificateException e) {
                e.printStackTrace();
            } finally {
                try {
                    instream.close();
                } catch (Exception ignore) {
                }
            }
            // 相信自己的CA和所有自签名的证书
            SSLContext sslcontext = SSLContexts.custom().loadTrustMaterial(trustStore, new TrustSelfSignedStrategy()).build();
            // 只允许使用TLSv1协议
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" }, null,
                    SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
            httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
            // 创建http请求(get方式)
            HttpGet httpget = new HttpGet("https://localhost:8443/myDemo/Ajax/serivceJ.action");
            System.out.println("executing request" + httpget.getRequestLine());
            CloseableHttpResponse response = httpclient.execute(httpget);
            try {
                HttpEntity entity = response.getEntity();
                System.out.println("----------------------------------------");
                System.out.println(response.getStatusLine());
                if (entity != null) {
                    System.out.println("Response content length: " + entity.getContentLength());
                    System.out.println(EntityUtils.toString(entity));
                    EntityUtils.consume(entity);
                }
            } finally {
                response.close();
            }
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (httpclient != null) {
                try {
                    httpclient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }*/


//    public static void main(String[] args) {
//        //String doGet = doGet("http://172.16.0.31:16010/jmx?qry=Hadoop:service=HBase,name=Master,sub=Balancer");
//        List<BasicNameValuePair> parmsList = new ArrayList<BasicNameValuePair>();
//        BasicNameValuePair userPa = new BasicNameValuePair("userId", "100");
//        parmsList.add(new BasicNameValuePair("userId", "101"));
//        parmsList.add(new BasicNameValuePair("userType", "1"));
////        String doPost = doPost("http://192.168.0.168:9093/user/saveAccount",parmsList);
////        String doPost = doPost("http://192.168.0.168:9093/user/saveAccount",parmsList);
//        //JSONObject jsonObject = JsonSwitchUtils.get(doPost);
//        JSONObject jsonObject = new JSONObject();
//        try {
//             //= JSONObject.parseArray(doPost);
//             jsonObject = JSONObject.parseObject(doPost);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        //String msg = jsonObject.get("msg");
//        Object code = jsonObject.get("code");
//        int codeInt = Integer.parseInt(code + "");
//
//        System.out.println(doPost);
//        System.out.println(codeInt);
//        //System.out.println(jsonObject.get("name"));
//    }

}