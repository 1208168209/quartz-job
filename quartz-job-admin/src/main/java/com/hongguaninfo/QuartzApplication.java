package com.hongguaninfo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by chenqinglong on 2019/2/1 0001.
 */
@SpringBootApplication
@ComponentScan(basePackages = { "com.hongguaninfo" })
@MapperScan("com.hongguaninfo.quartz.mapper")
public class QuartzApplication extends SpringBootServletInitializer {
    public static void main(String[] args){
        SpringApplication.run(QuartzApplication.class, args);
    }

    /**
     * 继承SpringBootServletInitializer 实现configure 方便打war 外部服务器部署。
     * 需要把web项目打成war包部署到外部tomcat运行时需要改变启动方式
     * @param application
     * @return
     */
    @Override
    protected final SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
        return application.sources(QuartzApplication.class);
    }
}
