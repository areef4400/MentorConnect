package com.Project.MentorConnect.Dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class LoginResponseDto {
    String jwt;
    Integer userId;
}
