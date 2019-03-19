package com.workout.workoutArtifact.vaadin;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.workout.workoutArtifact.endpoint.domain.Exercise;
import com.workout.workoutArtifact.endpoint.domain.Muscle;
import com.workout.workoutArtifact.endpoint.facade.ExerciseFacade;
import com.workout.workoutArtifact.endpoint.facade.MuscleFacade;
import com.workout.workoutArtifact.vaadin.dto.ExerciseDto;
import com.workout.workoutArtifact.vaadin.dto.MuscleDto;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Route(value = "exercises")
public class ExerciseView extends HorizontalLayout {

  private final ExerciseFacade exerciseFacade;
  private final MuscleFacade muscleFacade;
  final Grid<ExerciseDto> exerciseDtoGrid = new Grid<>(ExerciseDto.class);

  final Grid<MuscleDto> muscleDtoGrid = new Grid<>(MuscleDto.class);

  public ExerciseView(
      ExerciseFacade exerciseFacade,
      MuscleFacade muscleFacade) {
    this.exerciseFacade = exerciseFacade;
    this.muscleFacade = muscleFacade;

    exerciseDtoGrid.setColumns("name", "type");
    exerciseDtoGrid.setWidth("60%");

    muscleDtoGrid.setColumns("name");
    muscleDtoGrid.setWidth("40%");

    add(exerciseDtoGrid, muscleDtoGrid);
    setSizeFull();
    listExercises();
    listMuscles();
  }

  private void listMuscles() {
    List<Muscle> muscleList = muscleFacade.getMusclesByName(Arrays.asList("*"));

    List<MuscleDto> muscleDtos = muscleList.stream()
        .map(muscle -> new MuscleDto(
            muscle.getMuscle().toString()
        )).collect(Collectors.toList());

    muscleDtoGrid.setItems(muscleDtos);
  }

  private void listExercises() {

    List<Exercise> exerciseList = exerciseFacade.getExercises(Arrays.asList("*"));

    List<ExerciseDto> exerciseDtos = exerciseList.stream()
        .map(exercise -> new ExerciseDto(
            exercise.getName().toString(),
            exercise.getIsMultiJoint() ? "COMPOUND" : "SINGLE", exercise.getMuscles().toString()))
        .collect(Collectors.toList());

    exerciseDtoGrid.setItems(exerciseDtos);
}

}