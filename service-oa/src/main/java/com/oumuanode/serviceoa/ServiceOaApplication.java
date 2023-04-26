package com.oumuanode.serviceoa;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.oumuanode")
public class ServiceOaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceOaApplication.class, args);
    }

}
