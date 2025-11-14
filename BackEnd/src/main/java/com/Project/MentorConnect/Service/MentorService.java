package com.Project.MentorConnect.Service;

import com.Project.MentorConnect.Model.MentorDto;
import com.Project.MentorConnect.Model.Mentors;
import com.Project.MentorConnect.Repository.MentorRepo;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.rmi.ServerException;
import java.util.*;


@Service
public class MentorService {

    MentorRepo mentorRepo;

    @Autowired
    MentorService(MentorRepo mentorRepo){
        this.mentorRepo = mentorRepo;
    }

    public ResponseEntity<String> addMentor(String mentorName, String expertise, MultipartFile profilePicture) throws IOException {
        try{
            Mentors mentor = new Mentors();
            mentor.setMentorName(mentorName);
            mentor.setExpertise(expertise);
            mentor.setProfilePicture(profilePicture.getBytes());

            mentorRepo.save(mentor);

            return new ResponseEntity<>("Mentor Added", HttpStatus.OK);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public ResponseEntity<List<MentorDto>> allMentors() {
        try{
            List<Mentors> allMentors = mentorRepo.findAll();
            List<MentorDto> mentors = new ArrayList<>();
            for(int i = 0; i < allMentors.size(); i++){
                if(allMentors.get(i).isSessionAvailable()){
                    MentorDto mentorDto = new MentorDto();
                    mentorDto.setMentorId(allMentors.get(i).getMentorId());
                    mentorDto.setMentorName(allMentors.get(i).getMentorName());
                    mentorDto.setCompanyName(allMentors.get(i).getCompanyName());
                    mentorDto.setSessions(allMentors.get(i).getSessions());
                    mentorDto.setExpertise(allMentors.get(i).getExpertise());
                    mentorDto.setProfilePicture(allMentors.get(i).getProfilePicture());
                    mentors.add(mentorDto);
                }
            }
            return new ResponseEntity<>(mentors, HttpStatus.OK);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

}
