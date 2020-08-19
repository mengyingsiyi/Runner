package com.runner.mail.controller;



import com.runner.commons.dto.EmailDto;
import com.runner.commons.vo.R;
import com.runner.mail.server.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: ebooks
 * @description
 * @author:
 * @create: 2020-08-07 16:58
 **/
@RestController
@RequestMapping("/api/email/")
public class MailController {
    @Autowired
//    private  MailService mailService;
    private MailService mailService;

    @PostMapping("/sendemail.do")
    public R sendEmail(@RequestBody EmailDto emailDto){
        return mailService.sendEmail(emailDto);
    }
}