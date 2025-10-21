package com.Project.MentorConnect.Service;

import com.Project.MentorConnect.Model.Mentors;
import com.Project.MentorConnect.Repository.MentorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;


@Service
public class MentorService {

    @Autowired
    MentorRepo mentorRepo;

    public ResponseEntity<String> addMentor(String mentorName, String expertise, MultipartFile profilePicture) throws IOException {
        Mentors mentor = new Mentors();
        mentor.setMentorName(mentorName);
        mentor.setExpertise(expertise);
        mentor.setProfilePicture(profilePicture.getBytes());

        mentorRepo.save(mentor);

        return new ResponseEntity<>("Mentor Added", HttpStatus.OK);
    }

    public ResponseEntity<List<Mentors>> allMentors() {
        List<Mentors> allMentors = mentorRepo.findAll();
        List<Mentors> mentors = new ArrayList<>();
        for(int i = 0; i < allMentors.size(); i++){
            if(allMentors.get(i).isSessionAvailable()){
                mentors.add(allMentors.get(i));
            }
        }
        return new ResponseEntity<>(mentors, HttpStatus.OK);
    }

}
