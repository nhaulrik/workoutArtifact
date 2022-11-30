package com.workout.workoutArtifact.mysql.entity;

import com.workout.workoutArtifact.session.Sport;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@RequiredArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "session")
public class SessionEntity {

  @Id
  private String id;

  @CreationTimestamp
  @Column(name = "create_date")
  private LocalDateTime createDate;

  @UpdateTimestamp
  @Column(name = "modify_date")
  private LocalDateTime modifyDate;

  @Column
  private LocalDateTime creationDateTime;

  @Column
  private String location;

  @Column
  private String programme;

  @Column
  private String splitName;

  @Column
  private Integer calories;

  @Column
  private Duration duration;

  @Column
  @Enumerated(EnumType.STRING)
  private Sport sport;

  @Column
  private Integer heartRateAverage;

  @Column
  private Integer heartRateMaximum;

  @ManyToOne
  @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "FK_session_user_id"))
  private UserEntity userEntity;

  @OneToMany(mappedBy = "sessionEntity", cascade = CascadeType.ALL)
  private List<WorkoutExerciseEntity> workoutExercises = new ArrayList<>();

  public UUID getId() {
    return UUID.fromString(this.id);
  }

  public void setId(UUID id) {
    this.id = id.toString();
  }

}
