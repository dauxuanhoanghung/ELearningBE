package com.dxhh.elearning.controllers;

import com.dxhh.elearning.dto.response.ModelResponse;
import com.dxhh.elearning.dto.response.stats.CountUserAndMonthResponse;
import com.dxhh.elearning.dto.response.stats.CountUserByRoleResponse;
import com.dxhh.elearning.dto.response.stats.CourseWithMostLecturesResponse;
import com.dxhh.elearning.dto.response.stats.CourseWithMostRegistrationsResponse;
import com.dxhh.elearning.services.StatsService;
import com.dxhh.elearning.services.UserService;
import com.dxhh.elearning.utils.Routing;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(originPatterns = "*")
@RestController
@RequestMapping(value = Routing.STATS, produces = MediaType.APPLICATION_JSON_VALUE)
public class StatsController {
    private final StatsService statsService;
    private final DateTimeFormatter dateTimeFormat;
    private final UserService userService;

    public StatsController(StatsService statsService, DateTimeFormatter dateTimeFormat, UserService userService) {
        this.statsService = statsService;
        this.dateTimeFormat = dateTimeFormat;
        this.userService = userService;
    }

    @GetMapping("/user-by-role")
    public ResponseEntity<ModelResponse> getCountUserByRole(@RequestParam Map<String, String> params) {
        String fromDate = params.get("fromDate");
        String toDate = params.get("toDate");
        LocalDateTime fromDateValue = null;
        LocalDateTime toDateValue = null;
        if (fromDate != null && !fromDate.isEmpty()) {
            fromDateValue = LocalDateTime.parse(fromDate, dateTimeFormat);
        }
        if (toDate != null && !toDate.isEmpty()) {
            toDateValue = LocalDateTime.parse(toDate, dateTimeFormat);
        }
        List<CountUserByRoleResponse> results = statsService.countNewUsersByRole(fromDateValue, toDateValue);
        ModelResponse res = new ModelResponse(200, "Get OK", results);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/course-with-most-lectures")
    public ResponseEntity<ModelResponse> getCourseByMostLectures(@RequestParam Map<String, String> params,
                                                                 @RequestParam(required = false, defaultValue = "5") int limit) {
        List<CourseWithMostLecturesResponse> results = statsService.countMostCourseByMostLecture(limit);

        ModelResponse res = new ModelResponse(200, "Get OK", results);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/course-with-most-registration")
    public ResponseEntity<ModelResponse> getCourseByMostRegistration(@RequestParam Map<String, String> params,
                                                                     @RequestParam(required = false, defaultValue = "5") int limit) {
        List<CourseWithMostRegistrationsResponse> results = statsService.countCourseByMostRegistration(limit);
        ModelResponse res = new ModelResponse(200, "Get OK", results);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/user-by-month")
    public ResponseEntity<ModelResponse> countNumberOfUserByMonth(@RequestParam(value = "year", required = false, defaultValue = "2024") int year) {
        List<CountUserAndMonthResponse> results = statsService.countNumberOfUserByMonth(year);
        ModelResponse res = new ModelResponse(200, "Get OK", results);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/user-register-until-month")
    public ResponseEntity<ModelResponse> countUserRegisterUntilMonth(@RequestParam(value = "year", required = false, defaultValue = "2023") int year) {
        List<CountUserAndMonthResponse> results = statsService.countUserRegisterUntilMonth(year);
        ModelResponse res = new ModelResponse(200, "Get OK", results);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/count-user-in-month-and-total")
    public ResponseEntity<ModelResponse> countUserInMonthAndTotal() {
        Map<String, Object> results = new HashMap<>();
        results.put("total", userService.count(new HashMap<>()));
        int year = LocalDateTime.now().getYear();
        int month = LocalDateTime.now().getMonthValue();
        var params = new HashMap<String, String>();
        params.put("startDate", LocalDateTime.of(year, month, 1, 0, 0, 0).format(dateTimeFormat));
        results.put("month", userService.count(params));
        ModelResponse res = new ModelResponse(200, "countUserInMonthAndTotal", results);
        return ResponseEntity.ok(res);
    }
}
