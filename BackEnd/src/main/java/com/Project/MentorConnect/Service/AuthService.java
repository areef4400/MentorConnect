package com.Project.MentorConnect.Service;

import com.Project.MentorConnect.Dto.LoginRequestDto;
import com.Project.MentorConnect.Dto.LoginResponseDto;
import com.Project.MentorConnect.Dto.SignupRequestDto;
import com.Project.MentorConnect.Dto.SignupResponseDto;
import com.Project.MentorConnect.Model.Users;
import com.Project.MentorConnect.Repository.UserRepo;
import com.Project.MentorConnect.Security.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepo userRepo;
    private final AuthUtil authUtil;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
//        System.out.println("In Auth Service");

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(), loginRequestDto.getPassword())
        );

        Users user = (Users) authentication.getPrincipal();
        String token = authUtil.generateAccessToken(user);
        return new LoginResponseDto(token, user.getUserId());
    }

    public SignupResponseDto signup(SignupRequestDto signupRequestDto) {

        Users user = userRepo.findByEmail(signupRequestDto.getEmail()).orElse(null);
        if(user != null)throw new IllegalArgumentException("User already Exist");

        user = userRepo.save(user.builder()
                .userId(signupRequestDto.getUserId())
                .username(signupRequestDto.getUsername())
                .email(signupRequestDto.getEmail())
                .password(passwordEncoder.encode(signupRequestDto.getPassword()))
                .availableSession(signupRequestDto.getAvailableSession())
                .build()
        );
        String token = authUtil.generateAccessToken(user);
        return new SignupResponseDto(user.getUserId(), token);
    }
}
