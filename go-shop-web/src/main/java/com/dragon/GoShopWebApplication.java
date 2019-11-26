package com.dragon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(scanBasePackages = "com.dragon")
@EnableJpaAuditing
@EnableAsync
public class GoShopWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoShopWebApplication.class, args);
    }

}
