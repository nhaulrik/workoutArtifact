package com.workout.workoutArtifact.infrastructure.mysqldatabase.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Data
@NoArgsConstructor
@Entity
@Table(name = "exercise")
@EqualsAndHashCode(exclude = "exerciseMuscleRelationEntities")
public class ExerciseEntity {

  @Id
  @Getter
  private String id;

  @CreationTimestamp
  @Column(name = "create_date")
  private LocalDateTime createDate;

  @UpdateTimestamp
  @Column(name = "modify_date")
  private LocalDateTime modifyDate;


  @Column
  private String name;

  @Column
  private Boolean isCompound;

  @Column
  private String bodyPart;

  @OneToMany(mappedBy = "exerciseEntity", cascade = CascadeType.ALL)
  private List<WorkoutExerciseEntity> workoutExercises = new ArrayList<>();

  @ManyToMany
  @JoinTable(name = "exercise_muscle",
      joinColumns = {@JoinColumn(name = "fk_exercise")},
      inverseJoinColumns = {@JoinColumn(name = "fk_muscle")})
  private List<MuscleEntity> muscleEntities = new ArrayList<>();

}
