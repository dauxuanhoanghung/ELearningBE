package com.dxhh.elearning.services.impl;

import com.dxhh.elearning.dto.response.stats.CountUserByRoleResponse;
import com.dxhh.elearning.dto.response.stats.CourseWithMostLecturesResponse;
import com.dxhh.elearning.dto.response.stats.CourseWithMostRegistrationsResponse;
import com.dxhh.elearning.repositories.StatsRepository;
import com.dxhh.elearning.services.StatsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class StatsServiceImpl implements StatsService {
    private final StatsRepository statsRepository;

    public StatsServiceImpl(StatsRepository statsRepository) {
        this.statsRepository = statsRepository;
    }

    @Override
    public List<CountUserByRoleResponse> countNewUsersByRole(LocalDateTime fromDate, LocalDateTime toDate) {
        return statsRepository.countNewUsersByRole(fromDate, toDate);
    }

    @Override
    public List<CourseWithMostLecturesResponse> countMostCourseByMostLecture(int limit) {
        return statsRepository.countMostCourseByMostLecture(limit);
    }

    @Override
    public List<CourseWithMostRegistrationsResponse> countCourseByMostRegistration(int limit) {
        return statsRepository.countCourseByMostRegistration(limit);
    }

}
