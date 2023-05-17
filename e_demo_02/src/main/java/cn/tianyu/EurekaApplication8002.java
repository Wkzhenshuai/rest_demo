package cn.tianyu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EurekaApplication8002 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication8002.class,args);
    }
}
