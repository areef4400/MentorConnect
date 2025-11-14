package com.Project.MentorConnect.Service;

import com.Project.MentorConnect.Model.Mentors;
import com.Project.MentorConnect.Model.Sessions;
import com.Project.MentorConnect.Model.Users;
import com.Project.MentorConnect.Repository.MentorRepo;
import com.Project.MentorConnect.Repository.SessionRepo;
import com.Project.MentorConnect.Repository.UserRepo;
import org.hibernate.service.spi.ServiceException;
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

}
