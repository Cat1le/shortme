package org.catile.shortme;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.sql.DataSource;

@SpringBootApplication
@EnableScheduling
public class ShortmeApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShortmeApplication.class, args);
    }
}
