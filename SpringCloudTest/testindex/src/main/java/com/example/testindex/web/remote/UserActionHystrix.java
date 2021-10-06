package com.example.testindex.web.remote;

import com.example.testindex.bean.User;
import org.springframework.stereotype.Component;

@Component
public class UserActionHystrix implements IUserAction {

    @Override
    public String user() {
        return "熔断: user";
    }

    @Override
    public String hello(String name) {
        return "熔断:你好啊"+name;
    }

    @Override
    public String add(int a, int b) {
        return "熔断: "+(a+b);
    }

    @Override
    public User show(User user) {
        user.setName("熔断");
        return  user;
    }
}
