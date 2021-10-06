package com.example.test1.web;

import com.example.test1.bean.User;
import com.example.test1.web.remote.IIndexAction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
public class UserAction {

    @GetMapping("user")
    public String user(HttpServletRequest request){

        return "user:"+request.getServerPort();
    }

    //RestTemplate远程调用
    @Resource
    RestTemplate restTemplate;

    @GetMapping("index")
    public String index(){
        String url="http://cloud-index/index";
        return restTemplate.getForObject(url,String.class);
    }
    @Resource
    IIndexAction iia;

    @GetMapping("indexForFeign")
    public String indexForFeign(){
        return iia.index();
    }


    @RequestMapping("hello")
    public String hello(String name){
        return "hello"+name;
    }


    @RequestMapping("add")
    public String add(int a,int b){
        return a+b+"";
    }

    //@RequestBody 请请求实体中加载参数
    @RequestMapping("show")
    public User show(@RequestBody User user){
        user.setAge(user.getId()+10);
        return user;
    }




}
