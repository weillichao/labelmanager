package com.bigdata.labelmanager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages= {"com.bigdata.datamanager.mapper"})
public class LabelmanagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LabelmanagerApplication.class, args);
	}
}
