package com.wsd.config;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tm
 * @version 1.0.0
 * @description tomcat配置类
 * @updateRemark
 * @updateUser
 * @createDate 2020-3-22 18:42
 * @updateDate 2020-3-22 18:42
 */
@Configuration
public class TomcatConfig {

    /**
     * 配置get请求可传特殊符号
     * @return
     */
    @Bean
    public TomcatServletWebServerFactory webServerFactory() {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        factory.addConnectorCustomizers(new TomcatConnectorCustomizer() {
            @Override
            public void customize(Connector connector) {
                connector.setProperty("relaxedPathChars", "\"<>[\\]^`{|}");
                connector.setProperty("relaxedQueryChars", "\"<>[\\]^`{|}");
            }
        });
        return factory;
    }
}
