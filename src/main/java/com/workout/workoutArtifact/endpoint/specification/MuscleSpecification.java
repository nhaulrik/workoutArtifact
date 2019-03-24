package com.workout.workoutArtifact.endpoint.specification;

import com.workout.workoutArtifact.backend.mysqldatabase.MuscleEntity;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@RequiredArgsConstructor
public class MuscleSpecification implements Specification<MuscleEntity> {

  @NonNull
  private SearchCriteria criteria;

  @Override
  public Predicate toPredicate(Root<MuscleEntity> root, CriteriaQuery<?> criteriaQuery,
      CriteriaBuilder criteriaBuilder) {
    if (criteria.getOperation().equalsIgnoreCase(">")) {
      return criteriaBuilder.greaterThanOrEqualTo(
          root.<String>get(criteria.getKey()), criteria.getValue().toString());
    } else if (criteria.getOperation().equalsIgnoreCase("<")) {
      return criteriaBuilder.lessThanOrEqualTo(
          root.<String>get(criteria.getKey()), criteria.getValue().toString());
    } else if (criteria.getOperation().equalsIgnoreCase(":")) {
      if (root.get(criteria.getKey()).getJavaType() == String.class) {
        return criteriaBuilder.like(
            root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
      } else {
        return criteriaBuilder.equal(root.get(criteria.getKey()), criteria.getValue());
      }
    }
    return null;
  }

  @Data
  @AllArgsConstructor
  public static class SearchCriteria {
    private String key;
    private String operation;
    private Object value;
  }

}
