package com.Project.MentorConnect.Controller;

import com.Project.MentorConnect.Model.Sessions;
import com.Project.MentorConnect.Model.SessionsDto;
import com.Project.MentorConnect.Model.Users;
import com.Project.MentorConnect.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
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

    @PostMapping("/addSession/{email}/{mentorId}")
    public ResponseEntity<String> addSession(@PathVariable String email, @PathVariable Integer mentorId) throws IOException {
        try{
            return userService.addSession(email, mentorId);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/allSessions/{email}")
    public ResponseEntity<List<SessionsDto>> allSessions(@PathVariable String email){
        try{
            return userService.allSessions(email);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/up-coming-session/{email}")
    public ResponseEntity<List<SessionsDto>> upComingSession(@PathVariable String email){
        try{
            return userService.upComingSession(email);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
