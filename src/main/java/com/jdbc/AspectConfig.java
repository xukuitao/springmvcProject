package com.jdbc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by Administrator on 2017/12/18.
 */
@ComponentScan
@EnableAspectJAutoProxy
@Configuration
public class AspectConfig {
    @Bean
    public DataSourceAspect bean(){
        return new DataSourceAspect();
    }
}
