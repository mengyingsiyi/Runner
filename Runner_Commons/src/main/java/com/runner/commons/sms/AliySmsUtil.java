package com.runner.commons.sms;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import lombok.extern.slf4j.Slf4j;

/**
 * @program: OpenMain
 * @description:为用户发送验证码
 * @author:
 * @create: 2020-07-28 20:51
 **/
@Slf4j
public class AliySmsUtil {
    private static String key = "LTAI4G55jTKTHNtuBGDbAHdD";
    private static String keysecret = "UoY5SJXUrkq0wL8Uk9Atrqyga4Q7pF";


    /**
     * 发送验证码
     * @param phone 手机号
     * @param code   验证码
     */
    public static boolean sendSmsCode(String phone,String code){
        System.err.println(phone+"---"+code);
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou",
                key, keysecret);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", "Running");
        request.putQueryParameter("TemplateCode", "SMS_200180370");
        request.putQueryParameter("TemplateParam", "{\"code\":"+code+"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            log.info(response.getData());
            return true;
        } catch (ServerException e) {
            e.printStackTrace();
            return false;
        } catch (ClientException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 密码找回验证码
     * @param phone 手机号
     * @param code 验证码*/
    public static boolean sendSmsFindCode(String phone,int code){
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou",
                "<accessKeyId>", "<accessSecret>");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", "来自Runner的短信");
        request.putQueryParameter("TemplateCode", "SMS_177258097");
        request.putQueryParameter("TemplateParam", "{\"code\":"+code+"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            //log.info(response.getData());
            return true;
        } catch (ClientException e) {
            e.printStackTrace();
            return false;
        }
    }

}
