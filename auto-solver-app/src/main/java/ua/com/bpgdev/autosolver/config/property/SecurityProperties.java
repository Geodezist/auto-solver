package ua.com.bpgdev.autosolver.config.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "autosolver.security")
public class SecurityProperties {
    private String tokenName;
}