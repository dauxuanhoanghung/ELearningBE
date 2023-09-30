package com.dxhh.elearning.services;

import com.dxhh.elearning.pojos.Lecture;
import com.dxhh.elearning.pojos.User;
import com.dxhh.elearning.pojos.UserNote;

import java.util.List;

public interface UserNoteService {
    UserNote create(UserNote userNote);
    UserNote getById(Integer id);
    List<UserNote> findByUser(User user);
    List<UserNote> findByUserAndLecture(Lecture lecture);
    boolean deleteById(Integer id);
}
