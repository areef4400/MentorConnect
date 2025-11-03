package com.Project.MentorConnect.Controller;

import com.Project.MentorConnect.Model.Mentors;
import com.Project.MentorConnect.Service.MentorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/mentor")
@CrossOrigin(origins = "http://localhost:5173")
public class MentorController {

    @Autowired
    MentorService mentorService;

    @PostMapping("/addMentor")
    public ResponseEntity<String> addMentor(@RequestParam String mentorName, @RequestParam String expertise,
                                            @RequestParam MultipartFile profilePicture) throws IOException {
        return mentorService.addMentor(mentorName, expertise, profilePicture);
    }

    @GetMapping("/allMentors")
    public ResponseEntity<List<Mentors>> allMentors(){
        try{
            return mentorService.allMentors();
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
