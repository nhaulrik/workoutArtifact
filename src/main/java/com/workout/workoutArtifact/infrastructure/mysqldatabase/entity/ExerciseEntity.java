package com.workout.workoutArtifact.infrastructure.mysqldatabase.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "exercise")
public class ExerciseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Getter
  private Long id;

  @Column
  private String name;

  @Column
  private Boolean isMultiJoint;

  @Column
  private String bodyPart;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "muscle_exercise", joinColumns = @JoinColumn(name = "exercise_id"), inverseJoinColumns = @JoinColumn(name = "muscle_id"))
  private Set<MuscleEntity> muscleEntities = new HashSet<>();

  @OneToMany(mappedBy = "exerciseEntity", cascade=CascadeType.ALL)
  private Set<WorkoutSetEntity> workoutSets = new HashSet<>();

  public ExerciseEntity(String name, Boolean isMultiJoint, List<MuscleEntity> muscleEntities, String primaryBodyPart) {
    this.name = name;
    this.isMultiJoint = isMultiJoint;
    this.muscleEntities = muscleEntities.stream().collect(Collectors.toSet());
    this.bodyPart = primaryBodyPart;
  }

  public ExerciseEntity(Long id) {
    this.id = id;
  }
}
