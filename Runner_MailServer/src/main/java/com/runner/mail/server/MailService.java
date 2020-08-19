package com.runner.mail.server;


import com.runner.commons.dto.EmailDto;
import com.runner.commons.vo.R;

/**
 * @Description:
 * @author: ZackJun
 * @date: 2020/8/19  13:56
 */
public interface MailService {
    R sendEmail(EmailDto dto);
}
