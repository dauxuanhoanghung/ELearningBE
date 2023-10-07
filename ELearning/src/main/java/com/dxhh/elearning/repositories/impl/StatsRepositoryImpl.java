package com.dxhh.elearning.repositories.impl;

import com.dxhh.elearning.dto.response.stats.CountUserByRoleResponse;
import com.dxhh.elearning.dto.response.stats.CourseWithMostLecturesResponse;
import com.dxhh.elearning.dto.response.stats.CourseWithMostRegistrationsResponse;
import com.dxhh.elearning.repositories.StatsRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StatsRepositoryImpl implements StatsRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<CountUserByRoleResponse> countNewUsersByRole(LocalDateTime fromDate, LocalDateTime toDate) {
        String jpql = "SELECT r.id, r.name, COUNT(u.id) " +
                "FROM User u " +
                "LEFT JOIN u.userRoles ur " +
                "LEFT JOIN ur.role r " +
                "WHERE (:fromDate IS NULL OR u.createdDate >= :fromDate) " +
                "AND (:toDate IS NULL OR u.createdDate <= :toDate) " +
                "GROUP BY r.id, r.name";

        TypedQuery<Object[]> query = entityManager.createQuery(jpql, Object[].class);
        query.setParameter("fromDate", fromDate);
        query.setParameter("toDate", toDate);

        List<Object[]> results = query.getResultList();
        List<CountUserByRoleResponse> countResponses = new ArrayList<>();

        for (Object[] result : results) {
            Integer roleId = (Integer) result[0];
            String roleName = (String) result[1];
            Long userCount = (Long) result[2];
            CountUserByRoleResponse response = new CountUserByRoleResponse(roleId, roleName, userCount);
            countResponses.add(response);
        }
        return countResponses;
    }

    @Override
    public List<CourseWithMostLecturesResponse> countMostCourseByMostLecture(int limit) {
        String jpql = "SELECT c.id, c.name, COUNT(l.id) AS lectureCount " +
                "FROM Course c " +
                "LEFT JOIN c.sections s " +
                "LEFT JOIN s.lectures l " +
                "GROUP BY c.id, c.name " +
                "ORDER BY lectureCount DESC";

        TypedQuery<Object[]> query = entityManager.createQuery(jpql, Object[].class);
        query.setMaxResults(limit);
        List<Object[]> results = query.getResultList();

        List<CourseWithMostLecturesResponse> responseList = new ArrayList<>();
        for (Object[] result : results) {
            Integer id = (Integer) result[0];
            String name = (String) result[1];
            Long countLectures = (Long) result[2];
            responseList.add(new CourseWithMostLecturesResponse(id, name, countLectures));
        }
        return responseList;
    }

    @Override
    public List<CourseWithMostRegistrationsResponse> countCourseByMostRegistration(int limit) {
        String jpql = "SELECT c.id, c.name, COUNT(t.id) " +
                "FROM Course c " +
                "LEFT JOIN c.transactions t " +
                "GROUP BY c.id, c.name " +
                "ORDER BY COUNT(t.id) DESC";

        TypedQuery<Object[]> query = entityManager.createQuery(jpql, Object[].class);
        query.setMaxResults(limit);
        List<Object[]> results = query.getResultList();

        List<CourseWithMostRegistrationsResponse> responseList = new ArrayList<>();
        for (Object[] result : results) {
            Integer id = (Integer) result[0];
            String name = (String) result[1];
            Long countRegistration = (Long) result[2];
            responseList.add(new CourseWithMostRegistrationsResponse(id, name, countRegistration));
        }
        return responseList;
    }

    @Override
    public List<Object[]> countNumberOfUserByMonth(int year) {
        String jpql = "SELECT FUNCTION('MONTH', u.createdDate) AS month, COUNT(u.id) AS userCount " +
                "FROM User u " +
                "WHERE FUNCTION('YEAR', u.createdDate) = :year " +
                "GROUP BY FUNCTION('MONTH', u.createdDate)";
        TypedQuery<Object[]> query = entityManager.createQuery(jpql, Object[].class);
        query.setParameter("year", year);
        return query.getResultList();
    }
}
