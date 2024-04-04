package com.dxhh.elearning.repositories;

import com.dxhh.elearning.pojos.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
    List<User> findByUsername(String username);
    List<User> findByEmail(String email);
    boolean existsByUsername(String username);
}
