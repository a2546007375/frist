package com.example.test1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

//注册客户端服务
@EnableEurekaClient
@SpringBootApplication
//开启Feign
@EnableFeignClients
public class Test1UserApplication {

	public static void main(String[] args) {
		SpringApplication.run(Test1UserApplication.class, args);
	}

	//负载均衡
	@LoadBalanced
	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}

}
