package org.catile.shortme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ShortmeApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShortmeApplication.class, args);
    }
}
