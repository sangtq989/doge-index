package com.doge.index.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "doge")
public class DogeConfigProperties {
    private DogeDrive dogeDrive;
    private DogeFacebook dogeFacebook;

    @Data
    public static class DogeDrive{
        private String folderId;
    }

    @Data
    public static class DogeFacebook{
        private String accessToken;
        private String appSecret;
    }
}

