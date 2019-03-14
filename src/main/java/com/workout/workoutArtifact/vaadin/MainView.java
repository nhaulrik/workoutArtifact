package com.workout.workoutArtifact.vaadin;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.workout.workoutArtifact.endpoint.domain.Exercise;
import com.workout.workoutArtifact.endpoint.facade.ExerciseFacade;
import java.util.Arrays;
import java.util.List;

@Route(value = "vaadinhome")
public class MainView extends VerticalLayout {

  private final ExerciseFacade exerciseFacade;
  final Grid<Exercise> grid;

  public MainView(ExerciseFacade exerciseFacade) {
    this.exerciseFacade = exerciseFacade;
    this.grid = new Grid<>(Exercise.class);
    add(grid);
    listExercises();
  }

  private void listExercises() {

    List<Exercise> exerciseList = exerciseFacade.getExercises(Arrays.asList("*"));

    grid.setItems(exerciseList);
  }

}