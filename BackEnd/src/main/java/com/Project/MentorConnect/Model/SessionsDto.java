package com.Project.MentorConnect.Model;

import jakarta.persistence.GeneratedValue;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
public class SessionsDto {
    private Integer id;

    private String zoomLink;

    private LocalDate localDate;
    private LocalTime localTime;
    private String state;
}
