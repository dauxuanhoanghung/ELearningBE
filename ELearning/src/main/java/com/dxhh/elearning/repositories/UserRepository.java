package com.dxhh.elearning.repositories;

import com.dxhh.elearning.pojos.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
    @EntityGraph(value = "User.AllData", type = EntityGraph.EntityGraphType.LOAD)
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

    @Query("SELECT u.credit FROM User u WHERE u.id = :id")
    Double findCreditByUserId(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.credit = :credit WHERE u.id = :id")
    void updateCreditByUserId(@Param("id") Integer id, @Param("credit") Double credit);

    @Query("SELECT u.credit FROM User u WHERE u.username = :username")
    Double findCreditByUsername(@Param("username") String username);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.credit = :credit WHERE u.username = :username")
    void updateCreditByUsername(@Param("username") String username, @Param("credit") Double credit);


}
