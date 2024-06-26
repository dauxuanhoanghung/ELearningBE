package com.dxhh.elearning.specifications;

import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class GSpecification<T> implements Specification<T> {

    private final SearchCriteria criteria;

    public GSpecification(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        switch (criteria.getOperation()) {
            case EQUAL:
                return builder.equal(getNestedPath(root, criteria.getKey()), criteria.getValue());
            case NOT_EQUAL:
                return builder.notEqual(getNestedPath(root, criteria.getKey()), criteria.getValue());
            case LIKE:
                return builder.like(builder.lower(getNestedPath(root, criteria.getKey())),
                        "%" + criteria.getValue().toString().toLowerCase() + "%");
            case LESS_THAN:
                return builder.lessThan(getNestedPath(root, criteria.getKey()), (Comparable) criteria.getValue());
            case LESS_THAN_OR_EQUAL:
                return builder.lessThanOrEqualTo(getNestedPath(root, criteria.getKey()), (Comparable) criteria.getValue());
            case GREATER_THAN:
                return builder.greaterThan(getNestedPath(root, criteria.getKey()), (Comparable) criteria.getValue());
            case GREATER_THAN_OR_EQUAL:
                return builder.greaterThanOrEqualTo(getNestedPath(root, criteria.getKey()), (Comparable) criteria.getValue());
            case AND:
                return builder.and((Predicate[]) criteria.getValue());
            case OR:
                if (criteria.getValue() instanceof Predicate[]) {
                    return builder.or((Predicate[]) criteria.getValue());
                } else if (criteria.getValue() instanceof List) {
                    List<GSpecification<T>> specifications = (List<GSpecification<T>>) criteria.getValue();
                    return specifications.stream()
                            .map(spec -> spec.toPredicate(root, query, builder))
                            .reduce(builder::or)
                            .orElse(null);
                } else {
                    throw new IllegalArgumentException("Invalid value for OR operation: " + criteria.getValue());
                }
            default:
                return null;
        }
    }


    public static <T> Specification<T> toSpecification(List<SearchCriteria> criteriaList) {
        return criteriaList.stream()
                .map(criteria -> (Specification<T>) new GSpecification<>(criteria))
                .reduce(Specification::and)
                .orElse(null);
    }

    private <Y> Path<Y> getNestedPath(Root<T> root, String field) {
        String[] fields = field.split("\\.");
        Path<Y> path = root.get(fields[0]);

        for (int i = 1; i < fields.length; i++) {
            path = path.get(fields[i]);
        }

        return path;
    }
}