package com.runner.sportsapi.service.impl;


import com.runner.commons.constant.SystemConstant;
import com.runner.commons.dto.SportsDto.AddRiding;
import com.runner.commons.dto.SportsDto.CoursesiDto;
import com.runner.commons.vo.R;
import com.runner.entity.pojo.Running;
import com.runner.entity.pojo.Uactiriy;
import com.runner.entity.pojo.Uclass;
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
    public R add(Uclass uclass, HttpServletRequest request) {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.set(SystemConstant.TOKEN_HEADER,request.getHeader(SystemConstant.TOKEN_HEADER));
        HttpEntity<Uclass> requestEntity = new HttpEntity<>(uclass, requestHeaders);
        return restTemplate.postForObject("http://runnersportservice/sports/addcourses.do", requestEntity, R.class);
    }

    @Override
    public R checkc(Uclass uclass, HttpServletRequest request) {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.set(SystemConstant.TOKEN_HEADER,request.getHeader(SystemConstant.TOKEN_HEADER));
        HttpEntity<Uclass> requestEntity = new HttpEntity<>(uclass, requestHeaders);
        return restTemplate.postForObject("http://runnersportservice/sports/cheakcourses.do",requestEntity, R.class);
    }

    @Override
    public R addaciritys(Uactiriy uactiriy, HttpServletRequest request) {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.set(SystemConstant.TOKEN_HEADER,request.getHeader(SystemConstant.TOKEN_HEADER));
        HttpEntity<Uactiriy> requestEntity = new HttpEntity<>(uactiriy, requestHeaders);
        return restTemplate.postForObject("http://runnersportservice/sports/addaciritys.do", requestEntity, R.class);
    }

    @Override
    public R addrunning(Running running, HttpServletRequest request) {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.set(SystemConstant.TOKEN_HEADER,request.getHeader(SystemConstant.TOKEN_HEADER));
        HttpEntity<Running> requestEntity = new HttpEntity<>(running, requestHeaders);
        return restTemplate.postForObject("http://runnersportservice/sports/addrunning.do", requestEntity, R.class);
    }

    @Override
    public R addriding(AddRiding addRiding, HttpServletRequest request) {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.set(SystemConstant.TOKEN_HEADER,request.getHeader(SystemConstant.TOKEN_HEADER));
        HttpEntity<AddRiding> requestEntity = new HttpEntity<>(addRiding, requestHeaders);
        return restTemplate.postForObject("http://runnersportservice/sports/addriding.do", requestEntity, R.class);
    }

}