package com.Project.MentorConnect.Controller;

import com.Project.MentorConnect.Model.Sessions;
import com.Project.MentorConnect.Model.Users;
import com.Project.MentorConnect.Service.SessionService;
import com.Project.MentorConnect.Service.UserService;
import com.Project.MentorConnect.Service.ZoomMeetingService;
import jakarta.security.auth.message.config.AuthConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/session")
@CrossOrigin(origins = "http://localhost:5173")
public class SessionController {

    private final SessionService sessionService;
    private final ZoomMeetingService zoomMeetingService;

    @Autowired
    public SessionController(SessionService sessionService, ZoomMeetingService zoomMeetingService){
        this.sessionService = sessionService;
        this.zoomMeetingService = zoomMeetingService;
    }

    @GetMapping("/create-meeting/{topic}/{ldt}")
    public String createMeeting(@PathVariable String topic, @PathVariable LocalDateTime ldt) throws IOException {
        try{
            return zoomMeetingService.createMeeting(topic, ldt);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
