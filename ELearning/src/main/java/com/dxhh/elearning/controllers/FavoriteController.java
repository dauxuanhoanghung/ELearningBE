package com.dxhh.elearning.controllers;

import com.dxhh.elearning.dto.response.CourseInfoResponse;
import com.dxhh.elearning.dto.response.ModelResponse;
import com.dxhh.elearning.mappers.CourseMapper;
import com.dxhh.elearning.mappers.UserMapper;
import com.dxhh.elearning.pojos.FavoriteCourse;
import com.dxhh.elearning.services.CourseService;
import com.dxhh.elearning.services.FavoriteCourseService;
import com.dxhh.elearning.utils.Routing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(originPatterns = "*")
@RestController
@RequestMapping(path = Routing.FAVORITES, produces = MediaType.APPLICATION_JSON_VALUE)
public class FavoriteController {

    private final FavoriteCourseService favoriteCourseService;
    private final CourseMapper courseMapper;
    private final UserMapper userMapper;

    @Autowired
    public FavoriteController(FavoriteCourseService favoriteCourseService,
                              CourseMapper courseMapper,
                              UserMapper userMapper) {
        this.favoriteCourseService = favoriteCourseService;
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
        } else {
            ModelResponse response = new ModelResponse(HttpStatus.NO_CONTENT.value(), "Remove from wishlist", null);
            return ResponseEntity.ok(response);
        }
    }

    @GetMapping("/get-list")
    public ResponseEntity<ModelResponse> getListByUser(@RequestParam Map<String, String> params) {
        List<FavoriteCourse> favoriteCourses = favoriteCourseService.getByUser(params);
        List<CourseInfoResponse> courses = favoriteCourses.stream()
                .map(course -> {
                    CourseInfoResponse info = courseMapper.toInfo(course.getCourse());
                    info.setUser(userMapper.toResponse(course.getCourse().getCreator()));
                    return info;
                })
                .toList();

        ModelResponse response = new ModelResponse(HttpStatus.OK.value(),
                "Favorite courses retrieved successfully", courses);
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/get-total-page")
    public ResponseEntity<ModelResponse> getTotalPage(@RequestParam Map<String, String> params) {
        Integer totalPage = favoriteCourseService.countByCurrentUser(params);
        ModelResponse response = new ModelResponse(HttpStatus.OK.value(), "Total page retrieved successfully", totalPage);
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/count")
    public ResponseEntity<ModelResponse> count(@RequestParam Map<String, String> params) {
        Integer count = favoriteCourseService.countByCurrentUser(params);
        ModelResponse response = new ModelResponse(HttpStatus.OK.value(), "Count retrieved successfully", count);
        return ResponseEntity.ok(response);
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
