package com.runner.commons.util;

import com.baidu.aip.contentcensor.AipContentCensor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * @program: OpenMain
 * @description:对文本和图片进行过滤去除
 * @author:
 * @create: 2020-07-28 21:20
 **/
@Slf4j
public class CensorUtil {
    //设置APPID/AK/SK
    public static final String APP_ID = "17214730";
    public static final String API_KEY = "WnrcZnQNbveI7UG7sDroOm1K";
    public static final String SECRET_KEY = "FUui3jN440l9eLN8B335C3VS62HvtafU";
    private static final AipContentCensor contentCensor;

    /*实现文本敏感过滤*/
    static {
        contentCensor = new AipContentCensor(APP_ID, API_KEY, SECRET_KEY);
    }

    public static boolean checkText(String msg) throws JSONException {
        if (StringUtil.checkStr(msg)) {
            JSONObject jsonObject = contentCensor.textCensorUserDefined(msg);
//            log.info("文本审核的结果：" + jsonObject.getString("conclusion"));

            if (jsonObject.getInt("conclusionType") == 1) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * 图片 审核
     *
     * @param data 图片的内容
     */
    public static boolean checkImg(byte[] data) throws JSONException {
        if (data != null && data.length > 0) {
            JSONObject object = contentCensor.imageCensorUserDefined(data, null);
            //log.info("图像审核结果：" + object.getString("conclusion"));
            if (object.getInt("conclusionType") == 1) {
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

//    public static void main(String[] args) {
//        System.out.println(checkText("123"));
//    }

}
