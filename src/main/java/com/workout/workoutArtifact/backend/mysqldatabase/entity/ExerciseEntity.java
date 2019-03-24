package com.workout.workoutArtifact.backend.mysqldatabase.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor
@Data
@EqualsAndHashCode(exclude = "muscleEntities")

@Entity
@Table(name = "exercise")
public class ExerciseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NonNull
  @Column
  private String name;

  @NonNull
  @Column
  private Boolean isMultiJoint;

  @NonNull
  @Column
  private String primaryBodyPart;

  @NonNull
  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinTable(name = "exercise_muscle",
      joinColumns = @JoinColumn(name = "exercise_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "muscle_id", referencedColumnName = "id"))
  private Set<MuscleEntity> muscleEntities = new HashSet<>();

//  @NonNull
//  @OneToMany(mappedBy = "exerciseEntity", cascade = CascadeType.ALL)
//  private Set<WorkoutSetEntity> workoutSets = new HashSet<>();

  public ExerciseEntity(String name, Boolean isMultiJoint, List<MuscleEntity> muscleEntities, String primaryBodyPart) {
    this.name = name;
    this.isMultiJoint = isMultiJoint;
    this.muscleEntities = muscleEntities.stream().collect(Collectors.toSet());
    this.muscleEntities.forEach(x -> x.getExerciseSet().add(this));
    this.primaryBodyPart = primaryBodyPart;
  }
}
