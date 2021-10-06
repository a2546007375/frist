package com.example.test1.web.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("cloud-index")
public interface IIndexAction {

    @GetMapping("index")
    String index();
}
