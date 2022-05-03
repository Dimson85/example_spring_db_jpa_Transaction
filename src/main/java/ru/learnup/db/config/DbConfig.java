package ru.learnup.db.config;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Data
@EnableConfigurationProperties
@Configuration
@ComponentScan("ru.learnup.db")
@EnableAspectJAutoProxy
public class DbConfig {
    @Value("${config.db.url:}")
    private String dbUrl;

    @Value("${config.db.userName:}")
    private String userName;

    @Value("${config.db.password:}")
    private String password;
}
