package com.Project.MentorConnect.Controller;

import com.Project.MentorConnect.Model.Users;
import com.Project.MentorConnect.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    private final UserService userService;

    @Autowired
    UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/availableSessions/{email}")
    public ResponseEntity<Integer> availableSessions(@PathVariable String email){
        return userService.availableSessions(email);
    }

    @GetMapping("/userId/{email}")
    public ResponseEntity<Integer> findUserId(@PathVariable String email){
        return userService.findUserId(email);
    }

    @PostMapping("/increamentSession/{email}/{session}")
    public ResponseEntity<String> increamentSessions(@PathVariable String email, @PathVariable Integer session){
        return userService.increamentSessions(email, session);
    }

    @PostMapping("/decreamentSession/{email}")
    public ResponseEntity<String> decreamentSessions(@PathVariable String email){
        return userService.decreamentSessions(email);
    }
}
