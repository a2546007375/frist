package com.example.testindex.web.remote;

import com.example.testindex.bean.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//定义远程服务名,默认情况下同一个服务只能定义一个接口
//Spring 会使用动态代理技术 在IOC容器中创建该接口的Bean
@FeignClient(value = "cloud-user",fallback = UserActionHystrix.class)
public interface IUserAction {

    @RequestMapping("user")
    String user();

    //feign转发的请求，都会使用post方法转发

    //feign默认会会把参数封装到body中去，所以应该在接口方法哪里用@用RequestParam注解来传入参数
    //没加@RequestParam注解 feign会把这个参数当初简单参数封装到body
    @RequestMapping("hello") //这个映射地址是映射服务器上地址
    public String hello(@RequestParam String name);

    @RequestMapping("add")
    public String add(@RequestParam int a,@RequestParam  int b);

    @RequestMapping("show")
    public User show(User user);

}
