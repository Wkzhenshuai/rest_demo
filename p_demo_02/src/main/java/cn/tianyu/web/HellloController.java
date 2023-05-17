package cn.tianyu.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HellloController {
    @RequestMapping("/service/provider/hello")
    public String hello(){
        return "hello,Spring Cloud! 02";
    }
    @RequestMapping("/service/provider/test")
    public String test(){
        return "使⽤了Eureka注册中⼼的服务提供者！02";
    }
}
