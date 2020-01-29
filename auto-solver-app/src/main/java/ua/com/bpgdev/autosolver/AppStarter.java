package ua.com.bpgdev.autosolver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@ComponentScan({ "ua.com.bpgdev.autosolver", "ua.com.bpgdev.security"})
@EnableJpaRepositories({"ua.com.bpgdev.autosolver.dao.jdbc","ua.com.bpgdev.security.dao"})
@EntityScan({"ua.com.bpgdev.autosolver.entity","ua.com.bpgdev.security.model"})
@EnableCaching
public class AppStarter {
    public static void main(String[] args) {
        SpringApplication.run(AppStarter.class, args);
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
