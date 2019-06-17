package org.sut.cashmachine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.sut.cashmachine.config.AppProperties;
import org.sut.cashmachine.config.WebMvcConfig;

@SpringBootApplication
//@EnableAspectJAutoProxy
@EnableConfigurationProperties(AppProperties.class)
//@Import(EclipseLinkJpaConfiguration.class)
public class CashmachineApplication {

	public static void main(String[] args) {
		SpringApplication.run(CashmachineApplication.class, args);
	}

}
