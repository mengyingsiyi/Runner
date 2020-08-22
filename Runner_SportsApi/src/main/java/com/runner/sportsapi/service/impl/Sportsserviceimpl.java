package com.runner.sportsapi.service.impl;


import com.runner.commons.constant.SystemConstant;
import com.runner.commons.dto.SportsDto.CoursesiDto;
import com.runner.commons.dto.SportsDto.addDto;
import com.runner.commons.vo.R;
import com.runner.sportsapi.service.Sportsservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@Service
public class Sportsserviceimpl implements Sportsservice {
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public R actirity(String id) {

        return restTemplate.getForObject("http://runnersportservice/sports/actiritys.do?id=" + id, R.class);
    }

    @Override
    public R add(addDto addDto, HttpServletRequest request) {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.set(SystemConstant.TOKEN_HEADER,request.getHeader(SystemConstant.TOKEN_HEADER));
        HttpEntity<addDto> requestEntity = new HttpEntity<>(addDto, requestHeaders);
        return restTemplate.postForObject("http://runnersportservice/sports/addcourses.do", requestEntity, R.class);
    }

    @Override
    public R checkc(CoursesiDto coursesiDto, HttpServletRequest request) {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.set(SystemConstant.TOKEN_HEADER,request.getHeader(SystemConstant.TOKEN_HEADER));
        HttpEntity<CoursesiDto> requestEntity = new HttpEntity<>(coursesiDto, requestHeaders);
        return restTemplate.postForObject("http://runnersportservice/sports/cheakcourses.do",requestEntity, R.class);
    }

}