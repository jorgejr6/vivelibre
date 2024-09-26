package com.vivelibre.service;

import com.vivelibre.config.PropertiesConfig;
import com.vivelibre.model.AuthVivelibreResponse;
import com.vivelibre.model.VivelibreRequest;
import com.vivelibre.model.VivelibreResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

@Service
@Slf4j
public class VivelibreService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PropertiesConfig propertiesConfig;

    public VivelibreResponse callPostEndpoint() {
        log.debug("Initializing parameters...");
        Date date = new Date();
        String url = propertiesConfig.getUrl();

        // headers configuration
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        // request body
        VivelibreRequest requestBody = VivelibreRequest.builder().username(propertiesConfig.getUsername()).password(propertiesConfig.getPassword()).build();
        HttpEntity entity = new HttpEntity<>(requestBody, headers);

        // POST call
        log.info("Calling authz-vivelibre post {} and body {}", url, requestBody);
        ResponseEntity<AuthVivelibreResponse> authVivelibreResponseResponseEntity = restTemplate.postForEntity(url, entity, AuthVivelibreResponse.class);
        log.info("Called authz-vivelibre post with response: {}", authVivelibreResponseResponseEntity);

        // output format
        VivelibreResponse response = mapVivelibreResponse(date, Objects.requireNonNull(authVivelibreResponseResponseEntity.getBody()));
        log.debug("Mapped response: {}", response);
        return response;
    }

    private VivelibreResponse mapVivelibreResponse(Date date, AuthVivelibreResponse authVivelibreResponse) {
        String pattern = "MMMM dd, yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, Locale.ENGLISH);
        return VivelibreResponse.builder()
                .authViveLibreToken(authVivelibreResponse.getToken())
                .date(simpleDateFormat.format(date))
                .build();
    }
}
