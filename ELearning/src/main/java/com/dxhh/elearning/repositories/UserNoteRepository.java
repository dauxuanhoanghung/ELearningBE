package com.dxhh.elearning.repositories;

import com.dxhh.elearning.pojos.UserNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserNoteRepository extends JpaRepository<UserNote, Integer> {
}
