package com.Project.MentorConnect.Repository;

import com.Project.MentorConnect.Model.Sessions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepo extends JpaRepository<Sessions, Integer> {

}
