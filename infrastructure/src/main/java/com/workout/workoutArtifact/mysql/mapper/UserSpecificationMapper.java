package com.workout.workoutArtifact.mysql.mapper;

import com.workout.workoutArtifact.specification.AndSpecification;
import com.workout.workoutArtifact.specification.MatchAllSpecification;
import com.workout.workoutArtifact.user.User;
import com.workout.workoutArtifact.mysql.entity.UserEntity;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.mapping.MappingException;
import org.springframework.stereotype.Component;

@Component
public class UserSpecificationMapper {

  public Specification<UserEntity> toJpaSpecification(com.workout.workoutArtifact.specification.Specification<User> userSpecification) {

    if (userSpecification instanceof AndSpecification) {
      return ((AndSpecification<User>) userSpecification).getSet().stream()
          .map(this::toJpaSpecification)
          .reduce(org.springframework.data.jpa.domain.Specification::and)
          .orElse((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.disjunction());
    } else if (userSpecification instanceof MatchAllSpecification) {
      return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.conjunction();
    } else if (userSpecification instanceof User.IdsSpecification) {
      return (root, criteriaQuery, criteriaBuilder) -> root.get("id").in(((User.IdsSpecification) userSpecification).getIds().stream().map(UUID::toString).collect(Collectors.toList()));
    } else if (userSpecification instanceof User.FirstNameSpecification) {
      return (root, criteriaQuery, criteriaBuilder) -> root.get("firstName").in(((User.FirstNameSpecification) userSpecification).getFirstNames());
    } else if (userSpecification instanceof User.LastNameSpecification) {
      return (root, criteriaQuery, criteriaBuilder) -> root.get("lastName").in(((User.LastNameSpecification) userSpecification).getLastNames());
    }
    throw new MappingException("Unknown specification");
  }
}
