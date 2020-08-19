package com.runner.mail.server.impl;


import com.runner.commons.dto.EmailDto;
import com.runner.commons.vo.R;
import com.runner.mail.server.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Description:
 * @author: ZackJun
 * @date: 2020/8/19  13:56
 */
@Service
public class MailServiceImpl implements MailService {
    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String mail;

    @Override
    public R sendEmail(EmailDto dto) {
        SimpleMailMessage message=new SimpleMailMessage();
//        message.setBcc(); //密送
//        message.setCc();//抄送
//        message.setTo(); //收件人
//        message.setFrom(); //发件人
        message.setTo(dto.getRecemail());
        message.setSentDate(new Date());
        message.setSubject(dto.getTitle());
        message.setText(dto.getContent());
        message.setFrom(mail);
        mailSender.send(message);
        return R.ok();
    }
}
