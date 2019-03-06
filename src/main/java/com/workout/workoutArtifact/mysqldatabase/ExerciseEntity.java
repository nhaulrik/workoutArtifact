package com.workout.workoutArtifact.mysqldatabase;

import java.util.List;
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
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Builder
@Data
@Entity
@Table(name = "exercise")
public class ExerciseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NonNull
  @Column
  private String name;

  @NonNull
  @Column
  private Boolean isMultiJoint;

  @OneToMany(mappedBy = "exerciseEntity", cascade = CascadeType.ALL)
  private List<MuscleEntity> muscleEntities;
}
