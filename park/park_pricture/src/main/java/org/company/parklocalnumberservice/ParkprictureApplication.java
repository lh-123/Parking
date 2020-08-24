package org.company.parklocalnumberservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages ={"org.company"})
public class ParkprictureApplication {
    public static void main(String[] args) {
        SpringApplication.run(ParkprictureApplication.class, args);
    }

}
