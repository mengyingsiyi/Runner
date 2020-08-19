package com.runner.pay.pay.wxpay;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @program: FmOpen
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-08-13 10:12
 */
public class HttpUtil {
    //post请求
    public static String postXml(String url,String xml){
        //1、实例化请求对象
        HttpPost post=new HttpPost(url);
//        HttpGet;
//        HttpPut;
//        HttpDelete;
       try {
           //2、实例化请求实体
           HttpEntity entity=new StringEntity(xml,"UTF-8");
           post.setEntity(entity);
           //3、实例化客户端对象
           HttpClient client= HttpClientBuilder.create().build();
           //4、执行请求 获取响应结果
           HttpResponse response=client.execute(post);
           //5、验证http状态码
           if(response.getStatusLine().getStatusCode()==200){
               //6、返回响应结果
               return EntityUtils.toString(response.getEntity());
           }
        } catch (IOException e) {
            e.printStackTrace();
        }
       return null;
    }
}
