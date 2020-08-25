package com.runner.commons.oss;

import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;

/**
 * @program: voter
 * @description: 封装阿里云的OSS
 * @author: Feri(王永)
 * @create: 2020-07-17 11:28
 */
public class OssSingleCore {
    private OSS client;
    String endpoint = "https://oss-cn-beijing.aliyuncs.com";
    // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
    String accessKeyId = "***";
    String accessKeySecret = "***";
    // 您的回调服务器地址，如http://oss-demo.aliyuncs.com:23450或http://127.0.0.1:9090。
    String callbackUrl = "http://127.0.0.1:9090";
    //单例模式的实现 ：懒汉、饿汉
    //饿汉式 实现单例模式
    private static OssSingleCore core=new OssSingleCore();
    private OssSingleCore(){
        client=new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    }
    public static OssSingleCore getInstance(){
        return core;
    }
    //创建存储空间
    public void createBucket(String name){
        // 创建CreateBucketRequest对象。
        CreateBucketRequest createBucketRequest = new CreateBucketRequest("<yourBucketName>");
        // 创建存储空间。
        client.createBucket(createBucketRequest);
    }
    //验证存储空间是否存在
    public boolean checkBucket(String name){
        return client.doesBucketExist(name);
    }
    //上传  返回访问路径
    public String upload(String bname,String objname,byte[] data){
        //实现上传
        client.putObject(bname,objname,new ByteArrayInputStream(data));
        //实例化日历类
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.YEAR,3);
        //生成访问路径
        String url=client.generatePresignedUrl(bname,objname, calendar.getTime()).toString();
        return url;
    }
    //上传  返回访问路径 带上回调
    public String uploadCB(String bname,String objname,byte[] data) throws IOException {
        PutObjectRequest putObjectRequest = new PutObjectRequest(bname, objname,new ByteArrayInputStream(data));

        // 上传回调参数。
        Callback callback = new Callback();
        callback.setCallbackUrl(callbackUrl);
        callback.setCallbackBody("{\\\"mimeType\\\":${mimeType},\\\"size\\\":${size}}");
        // 设置发起回调请求的Content-Type。
        callback.setCalbackBodyType(Callback.CalbackBodyType.JSON);
        // 设置发起回调请求的自定义参数，由Key和Value组成，Key必须以x:开始。
        callback.addCallbackVar("x:var1", "value1");
        callback.addCallbackVar("x:var2", "value2");
        putObjectRequest.setCallback(callback);
        //实现上传
        PutObjectResult putObjectResult =client.putObject(putObjectRequest);
        //实例化日历类
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.YEAR,3);
        //生成访问路径
        String url=client.generatePresignedUrl(bname,objname, calendar.getTime()).toString();

        // 读取上传回调返回的消息内容。
        byte[] buffer = new byte[1024];
        int len=putObjectResult.getResponse().getContent().read(buffer);
        System.out.println(new String(buffer,0,len));
        // 数据读取完成后，获取的流必须关闭，否则会造成连接泄漏，导致请求无连接可用，程序无法正常工作。
        putObjectResult.getResponse().getContent().close();
        return url;
    }
    //校验对象是否存在
    public boolean checkObjName(String bname,String objname){
        return client.doesObjectExist(bname, objname);
    }

    public String video(String bucketName,String objectName) {
       String style = "video/snapshot,t_50000,f_jpg,w_800,h_600";
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.YEAR,3);
        GeneratePresignedUrlRequest req = new GeneratePresignedUrlRequest(bucketName, objectName, HttpMethod.GET);
        req.setExpiration(calendar.getTime());
        req.setProcess(style);
        URL signedUrl = client.generatePresignedUrl(req);
        System.out.println(signedUrl);
        client.shutdown();
        return signedUrl.toString();
    }


}
