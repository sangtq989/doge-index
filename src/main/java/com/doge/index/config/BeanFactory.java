package com.doge.index.config;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

@Configuration
public class BeanFactory {

    @Value("${facebook.accessToken}")
    private String accessToken;
    @Value("${facebook.appSecret}")
    private String appSecret;

    @Bean
    public Drive getService() throws GeneralSecurityException, IOException {
        GoogleCredential credential = GoogleCredential.fromStream(new ClassPathResource("static/gcp-vpn-368811-3b451d960f47.json").getInputStream())
                .createScoped(Collections.singleton(DriveScopes.DRIVE));
        HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
        return new Drive.Builder(httpTransport, jsonFactory, credential).setApplicationName("Index Doge").build();
    }

    @Bean
    public FacebookClient createClient() {
        return new DefaultFacebookClient(accessToken, appSecret, Version.LATEST);
    }

}
