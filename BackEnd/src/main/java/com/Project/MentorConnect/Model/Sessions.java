package com.Project.MentorConnect.Model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DialectOverride;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sessions {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String zoomLink;

    private LocalDate localDate;
    private LocalTime localTime;
    private String state;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Users user;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Mentors mentor;
}
