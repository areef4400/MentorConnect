package com.Project.MentorConnect.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mentors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer mentorId;
    private String mentorName;
    private String expertise;
    private String email;
    @Lob
    @JsonIgnore
    private byte[] profilePicture;
    private String CompanyName;
    private Integer sessions;

    private LocalTime sessionTime;
    private boolean sessionAvailable;

    @OneToMany(mappedBy = "mentor")
    private List<Sessions> sessionsList = new ArrayList<>();

}
