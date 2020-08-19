package com.runner.pay.controller;


import com.runner.pay.service.QrCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * @program: FmOpen
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-08-12 17:48
 */
@Controller
@RequestMapping("/api/qrcode")
public class QrCodeController {

    @Autowired
    private QrCodeService service;

    @GetMapping("/showqrcode/{msg}")
    public void showQrCode(@PathVariable String msg, HttpServletResponse response){
        service.createQrcode(msg, response);
    }
    @GetMapping("/payqrcode/{msg}")
    public void payQrCode(@PathVariable String msg, HttpServletResponse response){
        service.createQrcodePass(msg, response);
    }
}
