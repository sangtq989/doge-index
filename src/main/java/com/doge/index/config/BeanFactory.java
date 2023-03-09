package com.doge.index.config;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Configuration
public class BeanFactory {

    @Bean
    public Drive getService() throws GeneralSecurityException, IOException {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        List<String> urls = new ArrayList<String>();
        urls.add("https://www.googleapis.com/auth/drive");
        HttpTransport httpTransport = new NetHttpTransport();
        JacksonFactory jsonFactory = new JacksonFactory();
        ClassPathResource classPathResource = new ClassPathResource("static/gcp-vpn-368811-c50f1ff05763.p12");
        GoogleCredential credential= new GoogleCredential.Builder()
                .setTransport(httpTransport)
                .setJsonFactory(jsonFactory)
                .setServiceAccountId("dogeindex@gcp-vpn-368811.iam.gserviceaccount.com")
                .setServiceAccountScopes(urls)
                .setServiceAccountPrivateKeyFromP12File(classPathResource.getFile())
                .build();
        return new Drive.Builder(HTTP_TRANSPORT,
                JacksonFactory.getDefaultInstance(), credential)
                .build();
    }


}
