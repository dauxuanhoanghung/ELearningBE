package com.dxhh.elearning.services;

import com.dxhh.elearning.pojos.CourseComment;

import java.util.List;

public interface CourseCommentService {
    CourseComment create(CourseComment courseComment);

    CourseComment getById(Integer commentId);

    CourseComment update(Integer commentId, CourseComment updatedComment);

    boolean deleteById(Integer commentId);

    List<CourseComment> getByCourseId(Integer courseId, int page);
}
