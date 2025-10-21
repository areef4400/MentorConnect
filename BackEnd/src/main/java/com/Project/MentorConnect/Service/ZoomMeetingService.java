package com.Project.MentorConnect.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class ZoomMeetingService {

    @Autowired
    private ZoomService zoomService;

    public String createMeeting(String topic, LocalDateTime startTime) throws IOException {

        String token = zoomService.getAccessToken();

        String url = "https://api.zoom.us/v2/users/me/meetings";

        Map<String, Object> meetingDetails = new HashMap<>();
        meetingDetails.put("topic", topic);
        meetingDetails.put("type", 2);  // scheduled meeting
        meetingDetails.put("start_time", startTime.toString());
        meetingDetails.put("duration", 60);  // minutes
        meetingDetails.put("timezone", "Asia/Kolkata");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(meetingDetails, headers);
        RestTemplate rest = new RestTemplate();
        ResponseEntity<Map> resp = rest.postForEntity(url, entity, Map.class);
        Map<String, Object> body = resp.getBody();

        if (body == null || !body.containsKey("join_url")) {
            throw new IOException("Zoom API did not return join_url");
        }
        return (String) body.get("join_url");
    }
}
