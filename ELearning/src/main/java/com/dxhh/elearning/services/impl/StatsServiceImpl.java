package com.dxhh.elearning.services.impl;

import com.dxhh.elearning.dto.response.stats.CountUserAndMonthResponse;
import com.dxhh.elearning.dto.response.stats.CountUserByRoleResponse;
import com.dxhh.elearning.dto.response.stats.CourseWithMostLecturesResponse;
import com.dxhh.elearning.dto.response.stats.CourseWithMostRegistrationsResponse;
import com.dxhh.elearning.repositories.StatsRepository;
import com.dxhh.elearning.services.StatsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Override
    public List<CountUserAndMonthResponse> countNumberOfUserByMonth(int year) {
        List<Object[]> results = statsRepository.countNumberOfUserByMonth(year);
        Map<String, Long> userCountsByMonth = new LinkedHashMap<>();

        // Initialize the map with 0 counts for all months
        for (int month = 1; month <= 12; month++) {
            userCountsByMonth.put(Month.of(month).toString(), 0L);
        }

        for (Object[] result : results) {
            int month = (int) result[0];
            Long countUser = (Long) result[1];
            String monthName = Month.of(month).toString();
            userCountsByMonth.put(monthName, countUser);
        }

        List<CountUserAndMonthResponse> responseList = userCountsByMonth.entrySet().stream()
                .map(entry -> new CountUserAndMonthResponse(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
        return responseList;
    }

    @Override
    public List<CountUserAndMonthResponse> countUserRegisterUntilMonth(int year) {
        int currentYearValue = LocalDateTime.now().getYear();
        int currentMonthValue = currentYearValue == year ? LocalDateTime.now().getMonthValue() : 12;
        Map<String, Long> userCountsByMonth = new LinkedHashMap<>();
        for (int month = 1; month <= currentMonthValue; month++) {
            long count = statsRepository.countUserRegisterByMonth(month, year);
            userCountsByMonth.put(Month.of(month).toString(), count);
        }

        List<CountUserAndMonthResponse> responseList = userCountsByMonth.entrySet().stream()
                .map(entry -> new CountUserAndMonthResponse(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
        return responseList;
    }
}
