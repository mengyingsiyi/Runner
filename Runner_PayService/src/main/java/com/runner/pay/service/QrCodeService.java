package com.runner.pay.service;

import javax.servlet.http.HttpServletResponse;

/**
 * @program: FmOpen
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-08-12 17:44
 */
public interface QrCodeService {
    //明文生成
    void createQrcode(String msg, HttpServletResponse response);
    //密文
    void createQrcodePass(String msg, HttpServletResponse response);

}
