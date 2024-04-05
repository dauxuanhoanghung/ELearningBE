package com.dxhh.elearning.services.impl;

import com.dxhh.elearning.pojos.Course;
import com.dxhh.elearning.pojos.FavoriteCourse;
import com.dxhh.elearning.pojos.User;
import com.dxhh.elearning.repositories.FavoriteCourseRepository;
import com.dxhh.elearning.repositories.UserRepository;
import com.dxhh.elearning.services.CurrentUserService;
import com.dxhh.elearning.services.FavoriteCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FavoriteCourseServiceImpl extends CurrentUserService implements FavoriteCourseService {
    private final FavoriteCourseRepository favoriteCourseRepository;

    @Autowired
    public FavoriteCourseServiceImpl(FavoriteCourseRepository favoriteCourseRepository,
                                     UserRepository userRepository) {
        super(userRepository);
        this.favoriteCourseRepository = favoriteCourseRepository;
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
    @Cacheable(cacheNames = "wishlist.user")
    public List<FavoriteCourse> getByUser() {
        User user = getCurrentUser();
        return favoriteCourseRepository.findByUser(user);
    }
}
