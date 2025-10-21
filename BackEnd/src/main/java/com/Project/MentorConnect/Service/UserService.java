package com.Project.MentorConnect.Service;

import com.Project.MentorConnect.Model.Users;
import com.Project.MentorConnect.Repository.UserRepo;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    public ResponseEntity<String> register(Users user) {
        userRepo.save(user);
        return new ResponseEntity<>("Registered Successfully", HttpStatus.OK);
    }

    public ResponseEntity<Boolean> login(String email, String password) {
        Optional<Users> find = userRepo.findByEmail(email);
        if (find.isPresent()) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }


    public ResponseEntity<Integer> availableSessions(String email) {
        Optional<Users> find = userRepo.findByEmail(email);
        if (find.isPresent()) {
            Users user = find.get();
            return new ResponseEntity<>(user.getAvailableSession(), HttpStatus.OK);
        }
        return new ResponseEntity<>(-1, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> increamentSessions(String email, Integer session){
        Optional<Users> find = userRepo.findByEmail(email);
        if (find.isPresent()) {
            Users user = find.get();
            user.setAvailableSession(user.getAvailableSession() + session);
            userRepo.save(user);
            return new ResponseEntity<>("Session Increamented ", HttpStatus.OK);
        }
        return new ResponseEntity<>("Failed increament session", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> decreamentSessions(String email){
        Optional<Users> find = userRepo.findByEmail(email);
        if (find.isPresent()) {
            Users user = find.get();
            user.setAvailableSession(user.getAvailableSession()-1);
            userRepo.save(user);
            return new ResponseEntity<>("Session Decreamented", HttpStatus.OK);
        }
        return new ResponseEntity<>("Failed decreament session", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Integer> findUserId(String email) {
        Optional<Users> find = userRepo.findByEmail(email);
        if (find.isPresent()) {
            Users user = find.get();
            return new ResponseEntity<>(user.getUserId(), HttpStatus.OK);
        }
        return new ResponseEntity<>(-1, HttpStatus.NOT_FOUND);
    }
}
