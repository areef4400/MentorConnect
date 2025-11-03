package com.Project.MentorConnect.Controller;

import com.Project.MentorConnect.Model.Users;
import com.Project.MentorConnect.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/availableSessions/{email}")
    public ResponseEntity<Integer> availableSessions(@PathVariable String email){
        try{
            return userService.availableSessions(email);
        } catch (Exception e) {
            return  new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/userId/{email}")
    public ResponseEntity<Integer> findUserId(@PathVariable String email){
        try{
            return userService.findUserId(email);
        } catch (Exception e) {
            return  new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/increamentSession/{email}/{session}")
    public ResponseEntity<String> increamentSessions(@PathVariable String email, @PathVariable Integer session){
        try{
            return userService.increamentSessions(email, session);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/decreamentSession/{email}")
    public ResponseEntity<String> decreamentSessions(@PathVariable String email){
        try{
            return userService.decreamentSessions(email);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
