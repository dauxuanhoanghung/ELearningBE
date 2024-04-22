package com.dxhh.elearning.repositories;

import com.dxhh.elearning.pojos.User;
import com.dxhh.elearning.pojos.UserRole;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer>, JpaSpecificationExecutor<UserRole> {
    List<UserRole> findByUser(User user);
}
