package com.workout.workoutArtifact.mysql.entity.programme;

import java.util.Set;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "split_exercise")
public class SplitExerciseEntity {

  @Id
  private String id;
  private Integer splitExerciseNumber;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "split_exercise_id", foreignKey = @ForeignKey(name = "FK_split_exercise_set_split_exercise_id"))
  private Set<SplitExerciseSetEntity> splitExerciseSetEntitySet;

  public UUID getId() {
    return UUID.fromString(this.id);
  }

}
