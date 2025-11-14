package com.Project.MentorConnect.Model;

import jakarta.persistence.Lob;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Setter
@Getter
public class MentorDto {
    private Integer mentorId;
    private String mentorName;
    private String expertise;

    @Lob
    private byte[] profilePicture;
    private String CompanyName;
    private Integer sessions;

    private LocalTime sessionTime;
    private boolean sessionAvailable;
}
