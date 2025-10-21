package com.Project.MentorConnect.Repository;

import com.Project.MentorConnect.Model.Mentors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface MentorRepo extends JpaRepository<Mentors, Integer> {
}
