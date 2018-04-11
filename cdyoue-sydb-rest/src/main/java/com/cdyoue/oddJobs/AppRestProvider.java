package com.cdyoue.oddJobs;

import com.cdyoue.spring.common.DefaultProfileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.cdyoue.oddJobs")
//@EnableDiscoveryClient //@EnableEurekaClient //启动EnableEureka客户端
@EnableCaching
@EnableScheduling
public class AppRestProvider {
    private static final Logger log = LoggerFactory.getLogger(AppRestProvider.class);

//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(AppRestProvider.class);lg_portal_domain
//    }

    public static void main( String[] args ) throws UnknownHostException {
        SpringApplication app = new SpringApplication(AppRestProvider.class);
        DefaultProfileUtil.addDefaultProfile(app);
        Environment env = app.run(args).getEnvironment();
        log.info(
                "\n----------------------------------------------------------\n\t"
                        + "Application '{}' is running! Access URLs:\n\t" + "Local: \t\thttp://127.0.0.1:{}\n\t"
                        + "External: \thttp://{}:{}\n----------------------------------------------------------",
                env.getProperty("spring.application.name"), env.getProperty("server.port"),
                InetAddress.getLocalHost().getHostAddress(), env.getProperty("server.port"));
    }

}
