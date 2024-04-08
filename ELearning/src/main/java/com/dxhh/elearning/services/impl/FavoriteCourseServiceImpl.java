package com.dxhh.elearning.services.impl;

import com.dxhh.elearning.pojos.Course;
import com.dxhh.elearning.pojos.FavoriteCourse;
import com.dxhh.elearning.pojos.User;
import com.dxhh.elearning.repositories.FavoriteCourseRepository;
import com.dxhh.elearning.repositories.UserRepository;
import com.dxhh.elearning.services.CurrentUserService;
import com.dxhh.elearning.services.FavoriteCourseService;
import com.dxhh.elearning.specifications.GSpecification;
import com.dxhh.elearning.specifications.SearchCriteria;
import com.dxhh.elearning.specifications.SearchOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class FavoriteCourseServiceImpl extends CurrentUserService implements FavoriteCourseService {
    private final FavoriteCourseRepository favoriteCourseRepository;
    private final Environment env;

    @Autowired
    public FavoriteCourseServiceImpl(FavoriteCourseRepository favoriteCourseRepository,
                                     UserRepository userRepository,
                                     Environment env) {
        super(userRepository);
        this.favoriteCourseRepository = favoriteCourseRepository;
        this.env = env;
    }

    @Override
    public FavoriteCourse toggleFavorite(Course course) {
        User user = getCurrentUser();
        Optional<FavoriteCourse> existingFavorite = favoriteCourseRepository.findByUserAndCourse(user, course);

        if (existingFavorite.isPresent()) {
            favoriteCourseRepository.delete(existingFavorite.get());
            return null;
        } else {
            // The favorite doesn't exist, so create it
            FavoriteCourse newFavorite = new FavoriteCourse();
            newFavorite.setUser(user);
            newFavorite.setCourse(course);
            favoriteCourseRepository.save(newFavorite);
            return newFavorite;
        }
    }

    @Override
    public Optional<FavoriteCourse> getByCourseId(Integer id) {
        User user = getCurrentUser();
        Optional<FavoriteCourse> existingFavorite = favoriteCourseRepository.findByUserAndCourse(user, new Course(id));
        return existingFavorite;
    }

    @Override
    public List<FavoriteCourse> getByUser(Map<String, String> params) {
        int page = Integer.parseInt(params.get("page"));
        int pageNumber = Math.max(page, 0);
        int size = env.getProperty("SIZE", Integer.class, 8);
        if (params.containsKey("pageSize")) {
            size = Integer.parseInt(params.get("pageSize"));
        }
        Specification<FavoriteCourse> specification = toSpecification(params);
        Pageable pageable = PageRequest.of(pageNumber, size);

        return favoriteCourseRepository.findAll(specification, pageable).getContent();
    }

    @Override
    public Integer countByCurrentUser(Map<String, String> params) {
        Specification<FavoriteCourse> specification = toSpecification(params);
        return Math.toIntExact(this.favoriteCourseRepository.count(specification));
    }

    private Specification<FavoriteCourse> toSpecification(Map<String, String> params) {
        User user = this.getCurrentUser();
        List<SearchCriteria> criteriaList = new ArrayList<>();

        criteriaList.add(new SearchCriteria("user.id", SearchOperation.EQUAL, user.getId()));

        return GSpecification.toSpecification(criteriaList);
    }
}
