package com.wsd.generator;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tm
 * @version 1.0.0
 * @description mybatis-generator代码生成启动类
 * @updateRemark
 * @updateUser
 * @createDate 2020-3-28 20:25
 * @updateDate 2020-3-28 20:25
 */
public class StartGenerator {

    public void generator() throws Exception{

        List<String> warnings = new ArrayList<String>();
        //已经创建了是否覆盖
        boolean overwrite = true;
        //注意这里最好采用绝对路径，否则容易出现找不到文件
        //D:\idea_workspace\vcms\src\main\resources\generator.xml
        File configFile = new File("D:\\idea_workspace\\vcms\\src\\main\\resources\\generator\\generator.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config,
                callback, warnings);
        myBatisGenerator.generate(null);

    }

    public static void main(String[] args) throws Exception {
        try {
            StartGenerator generatorSqlmap = new StartGenerator();
            generatorSqlmap.generator();
            System.out.println("mybatis 代码生成成功。。。");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
