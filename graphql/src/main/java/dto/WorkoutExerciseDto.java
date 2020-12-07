package dto;

import com.workout.workoutArtifact.domain.exercise.model.Exercise;
import com.workout.workoutArtifact.domain.specification.AbstractSpecification;
import com.workout.workoutArtifact.domain.workoutset.model.WorkoutSet;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Value;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkoutExerciseDto {

  @NonNull
  private UUID id;
  private UUID sessionId;
  private UUID exerciseId;
  private Integer exerciseNumber;

  @Value
  public static class IdsSpecification extends AbstractSpecification<WorkoutExerciseDto> {

    private final List<UUID> ids;

    @Override
    public boolean isSatisfiedBy(WorkoutExerciseDto workoutExerciseDto) {
      return ids.contains(workoutExerciseDto.getId());
    }
  }

  @Value
  public static class SessionIdsSpecification extends AbstractSpecification<WorkoutExerciseDto> {

    private final List<UUID> sessionIds;

    @Override
    public boolean isSatisfiedBy(WorkoutExerciseDto workoutExerciseDto) {
      return sessionIds.contains(workoutExerciseDto.getSessionId());
    }
  }


}
