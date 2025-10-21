package com.Project.MentorConnect.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.sql.Time;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {
    private String err;
    private String message;
    private HttpStatus httpStatus;
}
