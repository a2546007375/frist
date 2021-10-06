package com.example.cloudzuul;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ZuulAction {
    @GetMapping("hello")//不能用zuul
    public String zuul(){
        return "zuul";
    }
}
