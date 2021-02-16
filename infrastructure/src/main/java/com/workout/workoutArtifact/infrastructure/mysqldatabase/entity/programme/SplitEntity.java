package com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.programme;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "split")
public class SplitEntity {

  @Id
  private String id;
  @Column
  private Integer number;
  @Column
  private Integer week;
  @Column
  private String name;
  @Column
  private DayOfWeek dayOfWeek;
  @Column
  private LocalDateTime creationDateTime;

  @ElementCollection
  @CollectionTable(
      name = "split_exercise_set",
      joinColumns = @JoinColumn(name = "split_id", foreignKey = @ForeignKey(name = "FK_split_exercise_set_split_id"))
  )
  private List<SplitExerciseSetEntity> splitExerciseSets;

  @ManyToOne
  @JoinColumn(name = "phase_id", foreignKey = @ForeignKey(name = "FK_split_phase_id"))
  private PhaseEntity phaseEntity;

  public UUID getId() {
    return UUID.fromString(this.id);
  }

}
