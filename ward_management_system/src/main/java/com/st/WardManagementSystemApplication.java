package com.st;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan(basePackages = "com.st.mapper")
public class WardManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(WardManagementSystemApplication.class, args);
    }

}
