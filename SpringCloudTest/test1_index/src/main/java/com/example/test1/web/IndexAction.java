package com.example.test1.web;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class IndexAction {

    @GetMapping("index")
    public String index(){
        return "index";
    }

    //RestTemplate远程调用
    @Resource
    RestTemplate restTemplate;

    @GetMapping("user")
    public String user(){
        String url="http://127.0.0.1:8001/user";
        return restTemplate.getForObject(url,String.class);
    }

}
