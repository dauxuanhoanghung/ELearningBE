package com.dxhh.elearning.repositories;

import com.dxhh.elearning.pojos.Lecture;
import com.dxhh.elearning.pojos.User;
import com.dxhh.elearning.pojos.UserNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserNoteRepository extends JpaRepository<UserNote, Integer>, JpaSpecificationExecutor<UserNote> {
    List<UserNote> findByUser(User user);

    List<UserNote> findByUserAndLecture(User user, Lecture lecture);
}
