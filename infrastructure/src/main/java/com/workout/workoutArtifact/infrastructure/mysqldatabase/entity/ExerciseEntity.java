package com.workout.workoutArtifact.infrastructure.mysqldatabase.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@EqualsAndHashCode(exclude = "exerciseMuscleRelationEntities")
public class ExerciseEntity {

  @Id
  @Getter
  private String id;

  @Column
  private String name;

  @Column
  private Boolean isCompound;

  @Column
  private String bodyPart;

  @OneToMany(mappedBy = "exerciseEntity", cascade = CascadeType.ALL)
  private List<WorkoutExerciseEntity> workoutExercises = new ArrayList<>();

}
