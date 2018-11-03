package com.hoshiumi.mathumi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.hoshiumi.mathumi.entity.UserEntity;
import com.hoshiumi.mathumi.mapper.UserMapper;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@MapperScan("com.hoshiumi.mathumi.mapper") 
@ServletComponentScan
public class MathumiApplication {
	
	@Autowired
    private UserMapper UserMapper;
	 
	public static void main(String[] args) {
		SpringApplication.run(MathumiApplication.class, args);
	}
	
	@Bean
    public void demo() {
	List<UserEntity> users = UserMapper.getAll();
        
        System.out.println(users.toString());
    	
    }
}
