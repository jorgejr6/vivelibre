package com.vivelibre.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "properties")
@Getter
@Setter
public class PropertiesConfig {

    private String url;
    private String username;
    private String password;
}
