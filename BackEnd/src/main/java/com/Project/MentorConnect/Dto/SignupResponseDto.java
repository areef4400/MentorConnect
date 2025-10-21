package com.Project.MentorConnect.Dto;


import lombok.*;

import java.security.PrivateKey;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SignupResponseDto {
    private Integer id;
    private String token;
}
