package com.workout.workoutArtifact.endpoint.ui.views;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.workout.workoutArtifact.endpoint.dto.ExerciseDto;
import com.workout.workoutArtifact.endpoint.facade.ExerciseFacade;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "exercises")
@PageTitle("Exercises overview")
public class ExerciseView extends HorizontalLayout {

  ExerciseFacade exerciseFacade;

  @Id("grid")
  private Grid<ExerciseDto> exerciseDtoGrid = new Grid<>(ExerciseDto.class);

  @Autowired
  public ExerciseView(ExerciseFacade exerciseFacade) {
    this.exerciseFacade = exerciseFacade;
    setupGrid();
    add(exerciseDtoGrid);
    setSizeFull();
    populateData();
  }

  private void setupGrid() {
    exerciseDtoGrid.setColumns("name", "type", "bodyPart");
  }

  private void populateData() {
    List<ExerciseDto> exerciseDtoList = exerciseFacade.getExercises(Arrays.asList("*"));
    exerciseDtoGrid.setItems(exerciseDtoList);
  }


}
