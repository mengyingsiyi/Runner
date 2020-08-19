package com.runner.pay.service.impl;


import com.runner.pay.qrcode.QrCodeUtil;
import com.runner.pay.service.QrCodeService;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Base64;

/**
 * @program: FmOpen
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-08-12 17:44
 */
@Service
public class QrCodeServiceImpl implements QrCodeService {
    @Override
    public void createQrcode(String msg, HttpServletResponse response) {
        BufferedImage image= QrCodeUtil.createQrCode(msg,400);
        try {
            ImageIO.write(image,"png",response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createQrcodePass(String msg, HttpServletResponse response) {
        //解密
        String m=new String(Base64.getUrlDecoder().decode(msg));

        BufferedImage image= QrCodeUtil.createQrCode(m,400);
        try {
            ImageIO.write(image,"png",response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
