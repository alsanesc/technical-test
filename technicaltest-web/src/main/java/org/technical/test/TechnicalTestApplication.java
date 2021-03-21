package org.technical.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"org.technical.test.*"})
@EntityScan(basePackages = {"org.technical.test.*"})
@EnableJpaRepositories(basePackages = {"org.technical.test.*"})
public class TechnicalTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TechnicalTestApplication.class);
    }

}
