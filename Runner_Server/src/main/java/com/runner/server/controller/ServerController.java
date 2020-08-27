package com.runner.server.controller;

import com.runner.commons.vo.R;
import com.runner.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @paragram: runner
 * @author: wangyang
 * @create: 2020/8/24 19:35
 * @Description:
 */
@RestController
public class ServerController {
    @Autowired
    private UserService service;

    @GetMapping("/getuserfromtoken")
    public R getUserFromToken(@RequestParam String token){
        System.out.println(token);
        return service.getUserFromToken(token);
    }
}
