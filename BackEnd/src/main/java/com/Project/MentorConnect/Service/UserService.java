package com.Project.MentorConnect.Service;

import com.Project.MentorConnect.Model.Mentors;
import com.Project.MentorConnect.Model.Sessions;
import com.Project.MentorConnect.Model.SessionsDto;
import com.Project.MentorConnect.Model.Users;
import com.Project.MentorConnect.Repository.MentorRepo;
import com.Project.MentorConnect.Repository.UserRepo;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepo userRepo;
    private final MentorRepo mentorRepo;
    private final ZoomMeetingService zoomMeetingService;
    private final MailService mailService;

    @Autowired
    public UserService(UserRepo userRepo, MentorRepo mentorRepo, ZoomMeetingService zoomMeetingService, MailService mailService){
        this.userRepo = userRepo;
        this.mentorRepo = mentorRepo;
        this.zoomMeetingService = zoomMeetingService;
        this.mailService = mailService;
    }

    public ResponseEntity<String> register(Users user) {
        userRepo.save(user);
        return new ResponseEntity<>("Registered Successfully", HttpStatus.OK);
    }

    public ResponseEntity<Boolean> login(String email, String password) {
        try{
            Optional<Users> find = userRepo.findByEmail(email);
            if (find.isPresent()) {
                return new ResponseEntity<>(true, HttpStatus.OK);
            }
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }


    public ResponseEntity<Integer> availableSessions(String email) {
        try{
            Optional<Users> find = userRepo.findByEmail(email);
            if (find.isPresent()) {
                Users user = find.get();
                return new ResponseEntity<>(user.getAvailableSession(), HttpStatus.OK);
            }
            return new ResponseEntity<>(-1, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public ResponseEntity<String> increamentSessions(String email, Integer session){
        try{
            Optional<Users> find = userRepo.findByEmail(email);
            if (find.isPresent()) {
                Users user = find.get();
                user.setAvailableSession(user.getAvailableSession() + session);
                userRepo.save(user);
                return new ResponseEntity<>("Session Increamented ", HttpStatus.OK);
            }
            return new ResponseEntity<>("Failed increament session", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public ResponseEntity<String> decreamentSessions(String email){
        try{
            Optional<Users> find = userRepo.findByEmail(email);
            if (find.isPresent()) {
                Users user = find.get();
                user.setAvailableSession(user.getAvailableSession()-1);
                userRepo.save(user);
                return new ResponseEntity<>("Session Decreamented", HttpStatus.OK);
            }
            return new ResponseEntity<>("Failed decreament session", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public ResponseEntity<Integer> findUserId(String email) {
        try{
            Optional<Users> find = userRepo.findByEmail(email);
            if (find.isPresent()) {
                Users user = find.get();
                return new ResponseEntity<>(user.getUserId(), HttpStatus.OK);
            }
            return new ResponseEntity<>(-1, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public ResponseEntity<String> addSession(String email, Integer mentorId) throws IOException {
        try{

            Optional<Users> userFound = userRepo.findByEmail(email);
            Optional<Mentors> mentorFound = mentorRepo.findById(mentorId);

            if(!userFound.isPresent() || !mentorFound.isPresent()){
                return new ResponseEntity<>("User Not Found Or Mentor Not Found", HttpStatus.NOT_FOUND);
            }
            Users user = userFound.get();
            Mentors mentor = mentorFound.get();

            Sessions session = new Sessions();
            if(user.getAvailableSession() > 0){

                ResponseEntity<String> res = decreamentSessions(email);

                LocalDate today = LocalDate.now();
                LocalDateTime ldt = LocalDateTime.of(today, mentor.getSessionTime());

                String zoomLink = zoomMeetingService.createMeeting("Mentor Session", ldt);

                session.setZoomLink(zoomLink);
                session.setLocalDate(today);
                session.setLocalTime(mentor.getSessionTime());
                session.setState("Upcoming");
                session.setMentor(mentor);
                session.setUser(user);


                List<Sessions> sessionsList = mentor.getSessionsList();
                sessionsList.add(session);
                mentor.setSessionsList(sessionsList);
                mentorRepo.save(mentor);

                List<Sessions> sessionsList2 = user.getSessionsList();
                sessionsList2.add(session);
                user.setSessionsList(sessionsList2);
                userRepo.save(user);

                String subject = "Your Session is Confirmed";
                String textToStudent = "Hello "+user.getUsername()+"\n" +
                        "\n" +
                        "Your session has been successfully booked.\n" +
                        "\n" +
                        "Mentor: "+mentor.getMentorName()+"\n" +
                        "Date: "+session.getLocalDate()+"\n" +
                        "Time: "+session.getLocalTime()+"\n" +
                        "\n" +
                        "Please be available on time for the session.  \n" +
                        "If you have any questions, feel free to reach out.\n" +
                        "\n" +
                        "Thank you,\n" +
                        "MentorConnect";

                String textToMentor = "Hello "+mentor.getMentorName()+"\n" +
                        "\n" +
                        "Your session has been successfully booked.\n" +
                        "\n" +
                        "Student: "+user.getUsername()+"\n" +
                        "Date: "+session.getLocalDate()+"\n" +
                        "Time: "+session.getLocalTime()+"\n" +
                        "\n" +
                        "Please be available on time for the session.  \n" +
                        "If you have any questions, feel free to reach out.\n" +
                        "\n" +
                        "Thank you,\n" +
                        "MentorConnect";


                mailService.sendMail(mentor.getEmail(),"mohammedareef35@gmail.com", textToMentor, subject);
                mailService.sendMail(user.getEmail(),"mohammedareef35@gmail.com", textToStudent, subject);

                return new ResponseEntity<>("Session Added", HttpStatus.OK);
            }
            return new ResponseEntity<>("No Subscription is available", HttpStatus.OK);
        }catch (Exception e){
            throw new ServiceException(e.getMessage());
        }
    }

    public ResponseEntity<List<SessionsDto>> allSessions(String email) {
        try{

            Optional<Users> user = userRepo.findByEmail(email);
            if(user.isPresent()){
                List<Sessions> sessionsList = user.get().getSessionsList();
                List<SessionsDto> resList = new ArrayList<>();

                for(int i = 0; i < sessionsList.size(); i++){
                    SessionsDto sessionsDto = new SessionsDto();

                    sessionsDto.setId(sessionsList.get(i).getId());
                    sessionsDto.setState(sessionsList.get(i).getState());
                    sessionsDto.setLocalDate(sessionsList.get(i).getLocalDate());
                    sessionsDto.setLocalTime(sessionsList.get(i).getLocalTime());
                    sessionsDto.setZoomLink(sessionsList.get(i).getZoomLink());

                    resList.add(sessionsDto);
                }
                return new ResponseEntity<>(resList, HttpStatus.OK);
            }
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }catch (Exception e){
            throw new ServiceException(e.getMessage());
        }
    }

    public ResponseEntity<List<SessionsDto>> upComingSession(String email) {
        try{
            Optional<Users> find = userRepo.findByEmail(email);
            if (find.isPresent()) {
                Users user = find.get();
                List<Sessions> list = user.getSessionsList();

                List<SessionsDto> resList = new ArrayList<>();

                for(int i = 0; i < list.size(); i++){
                    if(list.get(i).getState().equals("Upcoming")){
                        SessionsDto sessionsDto = new SessionsDto();

                        sessionsDto.setId(list.get(i).getId());
                        sessionsDto.setState(list.get(i).getState());
                        sessionsDto.setLocalDate(list.get(i).getLocalDate());
                        sessionsDto.setLocalTime(list.get(i).getLocalTime());
                        sessionsDto.setZoomLink(list.get(i).getZoomLink());

                        resList.add(sessionsDto);
                    }
                }
                Collections.sort(list, (a, b) -> (a.getId())-b.getId());
                return new ResponseEntity<>(resList, HttpStatus.OK);
            }
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
