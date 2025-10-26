package com.Project.MentorConnect.Controller;

import com.Project.MentorConnect.Dto.LoginRequestDto;
import com.Project.MentorConnect.Dto.LoginResponseDto;
import com.Project.MentorConnect.Dto.SignupRequestDto;
import com.Project.MentorConnect.Dto.SignupResponseDto;
import com.Project.MentorConnect.Service.AuthService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity.ok(authService.login(loginRequestDto));
    }

    @PostMapping("/signup")
    public ResponseEntity<SignupResponseDto> signup(@RequestBody SignupRequestDto signupRequestDto){
        return ResponseEntity.ok(authService.signup(signupRequestDto));
    }
}
