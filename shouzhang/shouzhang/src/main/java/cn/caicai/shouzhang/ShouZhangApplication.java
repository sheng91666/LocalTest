package cn.caicai.shouzhang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan(basePackages = {"cn.caicai.shouzhang.mapper"})
public class ShouZhangApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ShouZhangApplication.class);
    }
    public static void main(String[] args) {
        SpringApplication.run(ShouZhangApplication.class, args);
    }

}

