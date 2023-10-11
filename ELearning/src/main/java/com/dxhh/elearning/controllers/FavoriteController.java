package com.dxhh.elearning.controllers;

import com.dxhh.elearning.dto.response.CourseInfoResponse;
import com.dxhh.elearning.dto.response.ModelResponse;
import com.dxhh.elearning.mappers.CourseMapper;
import com.dxhh.elearning.mappers.UserMapper;
import com.dxhh.elearning.pojos.FavoriteCourse;
import com.dxhh.elearning.services.CourseService;
import com.dxhh.elearning.services.FavoriteCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/favorite/")
public class FavoriteController {

    private final FavoriteCourseService favoriteCourseService;
    private final CourseService courseService;
    private final CourseMapper courseMapper;
    private final UserMapper userMapper;
    @Autowired
    public FavoriteController(FavoriteCourseService favoriteCourseService, CourseService courseService, CourseMapper courseMapper, UserMapper userMapper) {
        this.favoriteCourseService = favoriteCourseService;
        this.courseService = courseService;
        this.courseMapper = courseMapper;
        this.userMapper = userMapper;
    }

    @PostMapping
    public ResponseEntity<ModelResponse> toggle(@RequestBody FavoriteCourse favoriteCourse) {
        FavoriteCourse createdFavoriteCourse = favoriteCourseService.toggleFavorite(favoriteCourse.getCourse());
        if (createdFavoriteCourse != null) {
            ModelResponse response = new ModelResponse(
                    HttpStatus.CREATED.value(), "Favorite course created successfully", createdFavoriteCourse);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }
        else {
            ModelResponse response = new ModelResponse(HttpStatus.NO_CONTENT.value(), "Delete OK", null);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        }
    }

    @GetMapping("/get-favorite-courses")
    public ResponseEntity<ModelResponse> getFavoriteCoursesByUser(@RequestParam Map<String, String> params) {
        List<FavoriteCourse> favoriteCourses = favoriteCourseService.getByUser();
        List<CourseInfoResponse> courses = new ArrayList<>();
        favoriteCourses.stream().forEach(c -> {
            CourseInfoResponse info = courseMapper.toInfo(c.getCourse());
            info.setCountRegistration(courseService.countRegistrationByCourseId(c.getCourse().getId()));
            info.setUser(userMapper.toResponse(c.getCourse().getCreator()));
            courses.add(info);
        });

        ModelResponse response = new ModelResponse(HttpStatus.OK.value(),
                "Favorite courses retrieved successfully",
                favoriteCourses);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/get-favor-by-course-id/{courseId}")
    public ResponseEntity<ModelResponse> getFavoriteCourseById(@PathVariable Integer courseId) {
        Optional<FavoriteCourse> favoriteCourse = favoriteCourseService.getByCourseId(courseId);
        if (favoriteCourse.isPresent()) {
            ModelResponse response = new ModelResponse(HttpStatus.OK.value(), "Favorite course retrieved successfully", favoriteCourse.get());
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            ModelResponse response = new ModelResponse(HttpStatus.NOT_FOUND.value(), "Favorite course not found", null);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }
}
