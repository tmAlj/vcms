package com.wsd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
/*添加mybatis的mapper全局扫描*/
@MapperScan(basePackages = {"com.wsd.mapper"})
/*开启事务*/
@EnableTransactionManagement
public class StartServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StartServerApplication.class, args);
    }

}
