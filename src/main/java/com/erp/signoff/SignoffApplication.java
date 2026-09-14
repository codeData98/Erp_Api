package com.erp.signoff;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.erp.signoff.mapper")
public class SignoffApplication {

    public static void main(String[] args) {
        SpringApplication.run(SignoffApplication.class, args);
    }
}
