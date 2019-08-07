package com.workout.workoutArtifact.endpoint.ui.views;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.workout.workoutArtifact.endpoint.dto.ExerciseDto;
import com.workout.workoutArtifact.endpoint.dto.MuscleDto;
import com.workout.workoutArtifact.endpoint.facade.ExerciseFacade;
import com.workout.workoutArtifact.endpoint.facade.MuscleFacade;
import com.workout.workoutArtifact.specification.MatchAllSpecification;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "exercises")
@PageTitle("Exercises overview")
public class ExerciseOverview extends HorizontalLayout {

  private final ExerciseFacade exerciseFacade;
  private final MuscleFacade muscleFacade;

  private Grid<ExerciseDto> exerciseDtoGrid = new Grid<>(ExerciseDto.class);
  private Grid<MuscleDto> muscleDtoGrid = new Grid<>(MuscleDto.class);

  @Autowired
  public ExerciseOverview(
      ExerciseFacade exerciseFacade,
      MuscleFacade muscleFacade) {
    this.exerciseFacade = exerciseFacade;
    this.muscleFacade = muscleFacade;

    exerciseDtoGrid.setColumns("name", "type", "bodyPart");
    exerciseDtoGrid.setItems(exerciseFacade.getExercises(Arrays.asList("*")));

    muscleDtoGrid.setColumns("name");
    muscleDtoGrid.setItems(muscleFacade.getMuscles(new MatchAllSpecification()));

    add(exerciseDtoGrid, muscleDtoGrid);
    setSizeFull();
  }

}
