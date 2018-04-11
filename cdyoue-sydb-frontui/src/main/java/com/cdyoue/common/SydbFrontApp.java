package com.cdyoue.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

@SpringBootApplication
@EnableZuulProxy
@ComponentScan(basePackages="com.cdyoue.common")
public class SydbFrontApp {

	public static void main(String[] args) {
		//SpringApplication.run(SydbApp.class, args);
		SpringApplication app = new SpringApplication(SydbFrontApp.class);


		DefaultProfileUtil.addDefaultProfile(app);
		Environment env = app.run(args).getEnvironment();
		RecordLogTools.info(
				"\n----------------------------------------------------------\n\t"
						+ "Application '{}' is running! Access URLs:\n\t" + "Local: \t\thttp://127.0.0.1:"+ env.getProperty("server.port") );


	}
}
