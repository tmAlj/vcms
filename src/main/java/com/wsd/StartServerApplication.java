package com.wsd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
/*添加mybatis的mapper全局扫描*/
@MapperScan(basePackages = {"com.wsd.mapper"})
public class StartServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StartServerApplication.class, args);
    }

}
