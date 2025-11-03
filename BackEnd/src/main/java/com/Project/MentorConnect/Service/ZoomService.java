package com.Project.MentorConnect.Service;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Base64;
import java.util.Map;

@Service
public class ZoomService {

    @Value("${zoom.accountId}")
    private String accountId;

    @Value("${zoom.clientId}")
    private String clientId;

    @Value("${zoom.clientSecret}")
    private String clientSecret;

    public String getAccessToken() throws IOException {
        try{
            String url = "https://zoom.us/oauth/token?grant_type=account_credentials&account_id=" + accountId;

            HttpHeaders headers = new HttpHeaders();
            String basicAuth = Base64.getEncoder()
                    .encodeToString((clientId + ":" + clientSecret).getBytes());
            headers.set("Authorization", "Basic " + basicAuth);
            headers.setAccept(MediaType.parseMediaTypes("application/json"));

            HttpEntity<Void> entity = new HttpEntity<>(headers);
            RestTemplate rest = new RestTemplate();

            ResponseEntity<Map> resp = rest.exchange(url, HttpMethod.POST, entity, Map.class);
            Map body = resp.getBody();
            if (body == null || !body.containsKey("access_token")) {
                throw new IOException("Failed to fetch Zoom access token");
            }
            return (String) body.get("access_token");
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
