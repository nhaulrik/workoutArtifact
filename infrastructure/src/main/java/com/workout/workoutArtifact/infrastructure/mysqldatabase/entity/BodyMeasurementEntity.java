package com.workout.workoutArtifact.infrastructure.mysqldatabase.entity;

import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "body_measurement")
public class BodyMeasurementEntity {

  @Id
  private String id;

  @CreationTimestamp
  @Column(name = "create_date")
  private LocalDateTime createDate;

  @UpdateTimestamp
  @Column(name = "modify_date")
  private LocalDateTime modifyDate;

  @Column
  private Double weight;
  @Column
  private Double chest;
  @Column
  private Double hip;
  @Column
  private Double stomach;
  @Column
  private Double leftThigh;
  @Column
  private Double rightThigh;
  @Column
  private Double leftCalve;
  @Column
  private Double rightCalve;
  @Column
  private Double leftBiceps;
  @Column
  private Double rightBiceps;
  @Column
  private Double leftForearm;
  @Column
  private Double rightForearm;

  @NonNull
  @ManyToOne
  @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "FK_body_measurement_user_id"))
  private UserEntity userEntity;

  public UUID getId() {
    return UUID.fromString(this.id);
  }

}
