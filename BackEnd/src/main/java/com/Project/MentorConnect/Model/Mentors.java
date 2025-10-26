package com.Project.MentorConnect.Model;

import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
public class Mentors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer mentorId;
    private String mentorName;
    private String expertise;
    @Lob
    private byte[] profilePicture;
    private String CompanyName;
    private Integer sessions;

    private LocalTime sessionTime;
    private boolean sessionAvailable;


    public Integer getMentorId() {
        return mentorId;
    }

    public void setMentorId(Integer mentorId) {
        this.mentorId = mentorId;
    }

    public String getMentorName() {
        return mentorName;
    }

    public void setMentorName(String mentorName) {
        this.mentorName = mentorName;
    }

    public String getExpertise() {
        return expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    public byte[] getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public Integer getSessions() {
        return sessions;
    }

    public void setSessions(Integer sessions) {
        this.sessions = sessions;
    }

    public LocalTime getSessionTime() {
        return sessionTime;
    }

    public void setSessionTime(LocalTime sessionTime) {
        this.sessionTime = sessionTime;
    }

    public boolean isSessionAvailable() {
        return sessionAvailable;
    }

    public void setSessionAvailable(boolean sessionAvailable) {
        this.sessionAvailable = sessionAvailable;
    }
}
