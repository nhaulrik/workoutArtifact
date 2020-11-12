package com.workout.workoutArtifact.infrastructure.mysqldatabase.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "session")
public class SessionEntity {

  @Id
  private String id;

  private LocalDateTime creationDateTime;

  @Column
  private String location;

  @Column
  private String programme;

  @Column
  private String splitName;

  @ManyToOne
  @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "FK_session_user_id"))
  private UserEntity userEntity;

  @OneToMany(mappedBy = "sessionEntity", cascade = CascadeType.REMOVE)
  private Set<WorkoutSetEntity> workoutSetEntities = new HashSet<>();

  public UUID getId() {
    return UUID.fromString(this.id);
  }

  public void setId(UUID id) {
    this.id = id.toString();
  }

}
