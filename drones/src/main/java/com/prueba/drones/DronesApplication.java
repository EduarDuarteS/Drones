package com.prueba.drones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;



@SpringBootApplication
@EntityScan("com.prueba.drones.model")
public class DronesApplication {
    public static void main(String[] args) {
        SpringApplication.run(DronesApplication.class, args);
    }
}
