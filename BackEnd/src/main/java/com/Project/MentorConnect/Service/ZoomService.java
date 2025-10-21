package com.Project.MentorConnect.Service;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Base64;
import java.util.Map;

@Service
public class ZoomService {
    private final String accountId = "UVk-0ThmTMKI74sWT355Wg";
    private final String clientId = "EbFNgPAvS6yQ40F6MHnaXQ";
    private final String clientSecret = "OPuTNYKx9nED8Qi0tzmxjAcEORC6zWZl";

    public String getAccessToken() throws IOException {
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
    }
}
