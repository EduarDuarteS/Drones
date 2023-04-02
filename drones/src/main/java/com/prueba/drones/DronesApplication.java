package com.prueba.drones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



@SpringBootApplication
@EntityScan("com.prueba.drones.model")
@EnableJpaRepositories(basePackages = "com.prueba.drones.repository")

public class DronesApplication {
    public static void main(String[] args) {
        SpringApplication.run(DronesApplication.class, args);
    }
}
