package com.dxhh.elearning.services;

import com.dxhh.elearning.dto.response.stats.CountUserAndMonthResponse;
import com.dxhh.elearning.dto.response.stats.CountUserByRoleResponse;
import com.dxhh.elearning.dto.response.stats.CourseWithMostLecturesResponse;
import com.dxhh.elearning.dto.response.stats.CourseWithMostRegistrationsResponse;

import java.time.LocalDateTime;
import java.util.List;

public interface StatsService {
    List<CountUserByRoleResponse> countNewUsersByRole(LocalDateTime fromDate, LocalDateTime toDate);

    List<CourseWithMostLecturesResponse> countMostCourseByMostLecture(int limit);
    List<CourseWithMostRegistrationsResponse> countCourseByMostRegistration(int limit);
    List<CountUserAndMonthResponse> countNumberOfUserByMonth(int year);
    List<CountUserAndMonthResponse> countUserRegisterUntilMonth(int year);
}
