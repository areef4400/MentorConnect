package com.Project.MentorConnect.Controller;

import com.Project.MentorConnect.Dto.LoginRequestDto;
import com.Project.MentorConnect.Dto.LoginResponseDto;
import com.Project.MentorConnect.Dto.SignupRequestDto;
import com.Project.MentorConnect.Dto.SignupResponseDto;
import com.Project.MentorConnect.Model.Users;
import com.Project.MentorConnect.Service.AuthService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto){
        try{
            return ResponseEntity.ok(authService.login(loginRequestDto));
        }catch(Exception e){
            return  new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<SignupResponseDto> signup(@RequestBody SignupRequestDto signupRequestDto){
        try{
            return ResponseEntity.ok(authService.signup(signupRequestDto));
        } catch (Exception e) {
            return  new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
