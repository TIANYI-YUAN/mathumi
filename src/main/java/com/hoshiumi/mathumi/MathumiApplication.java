package com.hoshiumi.mathumi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import javax.annotation.PostConstruct;

import com.hoshiumi.mathumi.entity.UserEntity;
import com.hoshiumi.mathumi.mapper.UserMapper;

import java.util.List;
import java.util.TimeZone;

import org.apache.catalina.Context;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.web.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@MapperScan("com.hoshiumi.mathumi.mapper") 
@ServletComponentScan
public class MathumiApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(MathumiApplication.class, args);
	}
	

	
	
}
