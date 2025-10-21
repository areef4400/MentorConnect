package com.Project.MentorConnect.Dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SignupRequestDto {
    private Integer userId;
    private String username;
    private String email;
    private String password;
    private Integer availableSession;
}
