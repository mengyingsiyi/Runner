package com.runner.sportsapi.service.impl;


import com.runner.commons.dto.SportsDto.checkcoureseDto;
import com.runner.commons.vo.R;
import com.runner.sportsapi.service.Sportsservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class Sportsserviceimpl implements Sportsservice {
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public R actirity(String id) {

        return restTemplate.getForObject("http://runnersportservice/sports/actiritys.do?id=" + id, R.class);
    }

    @Override
    public R add(String id) {
        return restTemplate.getForObject("http://runnersportservice/sports/addcourses.do?id=" + id, R.class);
    }

    @Override
    public R checkc(String phone) {
        return restTemplate.getForObject("http://runnersportservice/sports/cheakcourses.do?phone=" + phone, R.class);
    }

}