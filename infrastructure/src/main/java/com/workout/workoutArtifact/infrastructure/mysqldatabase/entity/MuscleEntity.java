package com.workout.workoutArtifact.infrastructure.mysqldatabase.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "muscle")
public class MuscleEntity {

  @Id
  @Getter
  private String id;

  @CreationTimestamp
  @Column(name = "create_date")
  private LocalDateTime createDate;

  @UpdateTimestamp
  @Column(name = "modify_date")
  private LocalDateTime modifyDate;


  @NonNull
  @Column
  private String name;

  @NonNull
  @Column
  private String bodyPart;

  @ManyToMany(mappedBy = "muscleEntities")
  private List<ExerciseEntity> exerciseEntities = new ArrayList<>();

  public UUID getId() {
    return UUID.fromString(this.id);
  }
}
