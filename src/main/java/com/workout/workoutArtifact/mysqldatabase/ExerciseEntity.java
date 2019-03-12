package com.workout.workoutArtifact.mysqldatabase;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

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
  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "exercise_muscle",
      joinColumns = @JoinColumn(name = "exercise_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "muscle_id", referencedColumnName = "id"))
  private Set<MuscleEntity> muscleEntities;

  public ExerciseEntity(String name, Boolean isMultiJoint, MuscleEntity... muscleEntities) {
    this.name = name;
    this.isMultiJoint = isMultiJoint;
    this.muscleEntities = Stream.of(muscleEntities).collect(Collectors.toSet());
    this.muscleEntities.forEach(x -> x.getExerciseSet().add(this));
  }
}
