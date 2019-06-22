package org.sut.cashmachine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.sut.cashmachine.config.AppProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class CashMachineApplication {

	public static void main(String[] args) {
		SpringApplication.run(CashMachineApplication.class, args);
	}

}
