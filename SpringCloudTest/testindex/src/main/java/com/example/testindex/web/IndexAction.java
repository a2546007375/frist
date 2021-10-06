package com.example.testindex.web;


import com.example.testindex.bean.User;
import com.example.testindex.web.remote.IUserAction;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@RestController
public class IndexAction {

    @GetMapping("index")
    public String index(HttpServletRequest request){
        return "index"+request.getServerPort();
    }

    //RestTemplate远程调用
    @Resource
    RestTemplate restTemplate;

    @GetMapping("user")
    public String user(){
        String url="http://cloud-user/user";
        return restTemplate.getForObject(url,String.class);
    }

    //Feign 远程调用
    @Resource
    IUserAction iua;

    @GetMapping("userForFeign")
    public String userForFeign(){
        return iua.user();
    }

    //feign默认会会把参数封装到body中去，所以应该在接口方法哪里用@用RequestParam注解来传入参数
    @RequestMapping("hello")
    public String hello(String name)
    {
        return iua.hello(name);
    }

    //feign规则:对多个参数装载到请求body中，只能有一个body
    //如果没有任何设置，会导致该错误
    //用RequestParam 这样参数就不会被封装到body中去了 在接口那边调用
    @RequestMapping("add")
    public String add(int a,int b)
    {
       return iua.add(a,b);

    }


    @RequestMapping("show")
    public User show(User user)
    {
        return iua.show(user);

    }

    @GetMapping(path="reqHeaders",consumes = MediaType.TEXT_HTML_VALUE)
    public String reqHeaders(HttpServletRequest req){
        String ret="";
        Enumeration<String> headerNames=req.getHeaderNames();
        while(headerNames.hasMoreElements())
        {
            String headerName=headerNames.nextElement();
            String handerValue=req.getHeader(headerName);
            ret+=headerName+":"+handerValue+"<br>\r\n";
        }
        return ret;
    }

}
