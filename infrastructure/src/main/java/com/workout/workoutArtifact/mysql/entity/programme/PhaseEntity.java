package com.workout.workoutArtifact.mysql.entity.programme;

import java.util.List;
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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "phase")
public class PhaseEntity {

  @Id
  private String id;
  @Column
  private Integer number;
  @Column
  private String name;
  @Column
  private String description;

  @OneToMany(mappedBy = "phaseEntity", cascade = CascadeType.ALL)
  private List<SplitEntity> splitEntities;

  @ManyToOne
  @JoinColumn(name = "programme_id", foreignKey = @ForeignKey(name = "FK_phase_programme_id"))
  private ProgrammeEntity programmeEntity;

  public UUID getId() {
    return UUID.fromString(this.id);
  }

}
