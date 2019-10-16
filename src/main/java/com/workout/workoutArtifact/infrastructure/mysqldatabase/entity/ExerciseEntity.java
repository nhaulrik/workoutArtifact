package com.workout.workoutArtifact.infrastructure.mysqldatabase.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "exercise")
@EqualsAndHashCode(exclude="exerciseMuscleRelationEntities")
public class ExerciseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Getter
  private Long id;

  @Column
  private String name;

  @Column
  private Boolean isCompound;

  @Column
  private String bodyPart;

  @OneToMany(mappedBy = "exerciseEntity", cascade = CascadeType.ALL)
  private Set<ExerciseMuscleRelationEntity> exerciseMuscleRelationEntities = new HashSet<>();

  @OneToMany(mappedBy = "exerciseEntity", cascade = CascadeType.ALL)
  private Set<WorkoutSetEntity> workoutSets = new HashSet<>();

}
