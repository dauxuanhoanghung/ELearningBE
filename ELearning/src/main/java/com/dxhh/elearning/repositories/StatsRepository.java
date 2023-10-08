package com.dxhh.elearning.repositories;

import com.dxhh.elearning.dto.response.stats.CountUserByRoleResponse;
import com.dxhh.elearning.dto.response.stats.CourseWithMostLecturesResponse;
import com.dxhh.elearning.dto.response.stats.CourseWithMostRegistrationsResponse;

import java.time.LocalDateTime;
import java.util.List;

public interface StatsRepository {

    List<CountUserByRoleResponse> countNewUsersByRole(LocalDateTime fromDate, LocalDateTime toDate);
    List<CourseWithMostLecturesResponse> countMostCourseByMostLecture(int limit);
    List<CourseWithMostRegistrationsResponse> countCourseByMostRegistration(int limit);
    List<Object[]> countNumberOfUserByMonth(int year);
    Long countUserRegisterByMonth(int month, int year);
}
