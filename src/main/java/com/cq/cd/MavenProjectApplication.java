package com.cq.cd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cq.cd.mapper")
public class MavenProjectApplication{
    public static void main(String[] args) {
	SpringApplication.run(MavenProjectApplication.class,args);
    }
}
