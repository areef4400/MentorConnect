package com.Project.MentorConnect.Service;

import com.Project.MentorConnect.Model.Mentors;
import com.Project.MentorConnect.Model.Sessions;
import com.Project.MentorConnect.Model.Users;
import com.Project.MentorConnect.Repository.MentorRepo;
import com.Project.MentorConnect.Repository.SessionRepo;
import com.Project.MentorConnect.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class SessionService {
    private final ZoomMeetingService zoomMeetingService;
    private final MentorRepo mentorRepo;
    private final UserService userService;
    private final SessionRepo sessionRepo;
    private final UserRepo userRepo;

    @Autowired
    SessionService(ZoomMeetingService zoomMeetingService, MentorRepo mentorRepo, UserService userService,
                   SessionRepo sessionRepo, UserRepo userRepo){
            this.zoomMeetingService = zoomMeetingService;
            this.mentorRepo = mentorRepo;
            this.userService = userService;
            this.sessionRepo = sessionRepo;
            this.userRepo = userRepo;
    }

    public ResponseEntity<String> addSession(String email, Integer mentorId) throws IOException {
        Sessions session = new Sessions();

        ResponseEntity<Integer> response = userService.findUserId(email);

        Users user = new Users();
        user.setUserId(response.getBody());
        session.setUser(user);

        Mentors mentors = new Mentors();
        mentors.setMentorId(mentorId);
        session.setMentor(mentors);
        if(user.getAvailableSession() > 0){
            ResponseEntity<String> res = userService.decreamentSessions(email);
            Optional<Mentors> mentor = mentorRepo.findById(mentorId);

            if(mentor.isPresent()){
                Mentors m1 = mentor.get();
                m1.setSessionAvailable(false);

                mentorRepo.save(m1);
                LocalDate today = LocalDate.now();

                LocalDateTime ldt = LocalDateTime.of(today, m1.getSessionTime());

                String zoomLink = zoomMeetingService.createMeeting("Mentor Session", ldt);
                session.setZoomLink(zoomLink);
                session.setLocalDate(today);
                session.setLocalTime(m1.getSessionTime());
                session.setState("Upcoming");

                sessionRepo.save(session);
            }
            return new ResponseEntity<>("Session Added", HttpStatus.OK);
        }
        return new ResponseEntity<>("No Subscription is available", HttpStatus.OK);
    }


    public ResponseEntity<List<Sessions>> allSessions(String email) {

        ResponseEntity<Integer> found = userService.findUserId(email);
        Integer userId = found.getBody();

        List<Sessions> list = sessionRepo.findAll();
        List<Sessions> sessions = new ArrayList<>();

        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getUser().getUserId() == userId){
                sessions.add(list.get(i));
            }
        }
        Collections.sort(list, (a, b) -> (a.getId()-b.getId()));
        return new ResponseEntity<>(sessions, HttpStatus.OK);
    }


    public ResponseEntity<List<Sessions>> upComingSession(String email) {

        Optional<Users> find = userRepo.findByEmail(email);
        if (find.isPresent()) {
            Users user = find.get();
            List<Sessions> list = sessionRepo.findAll();

            List<Sessions> sessions = new ArrayList<>();

            for(int i = 0; i < list.size(); i++){
                if(list.get(i).getUser().getUserId() == user.getUserId()
                        && list.get(i).getState().equals("Upcoming")){
                    sessions.add(list.get(i));
                }
            }
            Collections.sort(list, (a, b) -> (a.getId())-b.getId());
            return new ResponseEntity<>(sessions, HttpStatus.OK);
        }
        return new ResponseEntity<>(new ArrayList<Sessions>(), HttpStatus.NOT_FOUND);
    }
}
