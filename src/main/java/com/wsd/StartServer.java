package com.wsd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author tm
 * @version 1.0.0
 * @description 主程序入口
 * @updateRemark
 * @updateUser
 * @createDate 2020-3-28 20:25
 * @updateDate 2020-3-28 20:25
 */
@SpringBootApplication
/*添加mybatis的mapper全局扫描*/
@MapperScan(basePackages = {"com.wsd.mapper"})
/*开启事务*/
@EnableTransactionManagement
public class StartServer {

    public static void main(String[] args) {
        SpringApplication.run(StartServer.class, args);
    }

}
