package com.dxhh.elearning.repositories;

import com.dxhh.elearning.pojos.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
    @EntityGraph(attributePaths = {"userRoles"})
    List<User> findByUsername(String username);
    @EntityGraph(attributePaths = {"userRoles"})
    List<User> findByEmail(String email);
    @Query("SELECT u " +
            "FROM User u " +
            "JOIN u.userRoles ur " +
            "JOIN ur.role r " +
            "LEFT JOIN u.courses c " +
            "WHERE r.name = 'ROLE_LECTURER' " +
            "GROUP BY u " +
            "ORDER BY COUNT(c) DESC ")
    List<User> findTopLecturersByCoursesCount(Pageable pageable);
    boolean existsByUsername(String username);
}
