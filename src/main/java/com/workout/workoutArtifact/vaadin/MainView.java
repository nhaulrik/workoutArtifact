package com.workout.workoutArtifact.vaadin;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.workout.workoutArtifact.endpoint.domain.Exercise;
import com.workout.workoutArtifact.endpoint.facade.ExerciseFacade;
import com.workout.workoutArtifact.vaadin.dto.ExerciseDto;
import com.workout.workoutArtifact.vaadin.dto.MuscleDto;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Route(value = "vaadinhome")
public class MainView extends VerticalLayout {

  private final ExerciseFacade exerciseFacade;
  final Grid<ExerciseDto> exerciseGrid = new Grid<>(ExerciseDto.class);

  final Grid<MuscleDto> muscleDtoGrid = new Grid<>(MuscleDto.class);

  public MainView(ExerciseFacade exerciseFacade) {
    this.exerciseFacade = exerciseFacade;

    exerciseGrid.setColumns("name", "type");
    add(exerciseGrid);
    setSizeFull();
    listExercises();
  }

  private void listExercises() {

    List<Exercise> exerciseList = exerciseFacade.getExercises(Arrays.asList("*"));

    List<ExerciseDto> exerciseDtos = exerciseList.stream()
        .map(exercise -> new ExerciseDto(
            exercise.getName().toString(),
            exercise.getIsMultiJoint() ? "COMPOUND" : "SINGLE", exercise.getMuscles().toString()))
        .collect(Collectors.toList());

    exerciseGrid.setItems(exerciseDtos);
}

}