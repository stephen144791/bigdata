package cn.jt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import cn.jt.client.EurekaServiceFeign;

@RestController
public class HelloController {
	@Autowired
	private EurekaServiceFeign eurekaServiceFeign;
	@RequestMapping("/hello/{name}")
	@HystrixCommand(fallbackMethod="fallbackHello")
	public String hello(@PathVariable String name){
		return eurekaServiceFeign.hello(name);
	}
	//断路器业务失败时访问的方法
	public String fallbackHello(String name){
		return "tony";
	}
}
