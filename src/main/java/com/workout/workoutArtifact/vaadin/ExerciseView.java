package com.workout.workoutArtifact.vaadin;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.FooterRow;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;
import com.workout.workoutArtifact.common.Mapper;
import com.workout.workoutArtifact.endpoint.facade.ExerciseFacade;
import com.workout.workoutArtifact.endpoint.facade.MuscleFacade;
import com.workout.workoutArtifact.vaadin.dto.ExerciseDto;
import com.workout.workoutArtifact.vaadin.dto.MuscleDto;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Route(value = "exercises")
public class ExerciseView extends HorizontalLayout {

  private final ExerciseFacade exerciseFacade;
  private final MuscleFacade muscleFacade;
  final Grid<ExerciseDto> exerciseDtoGrid = new Grid<>(ExerciseDto.class);

  final Grid<MuscleDto> muscleDtoGrid = new Grid<>(MuscleDto.class);

  public ExerciseView(
      ExerciseFacade exerciseFacade,
      MuscleFacade muscleFacade, Mapper mapper) {
    this.exerciseFacade = exerciseFacade;
    this.muscleFacade = muscleFacade;

    exerciseDtoGrid.setColumns("name", "type", "bodyPart");
    exerciseDtoGrid.getColumnByKey("name").setHeader("Exercise");

    exerciseDtoGrid.setWidth("60%");
    exerciseDtoGrid.setSelectionMode(SelectionMode.SINGLE);
    exerciseDtoGrid.setHeight("80%");

    muscleDtoGrid.setColumns("name");
    muscleDtoGrid.setWidth("40%");
    muscleDtoGrid.setHeightByRows(true);

    Button addButton = new Button("Add Muscle", event -> {
    });

    FooterRow muscleGridFooter = muscleDtoGrid.appendFooterRow();
    muscleGridFooter.getCell(muscleDtoGrid.getColumnByKey("name")).setComponent(addButton);

    add(exerciseDtoGrid, muscleDtoGrid);
    setSizeFull();
    listExercises();
    listMuscles();

    exerciseDtoGrid.addSelectionListener(event -> {
      List<ExerciseDto> selectedExercise = new ArrayList<>(event.getAllSelectedItems());

      List<String> muscleNames = selectedExercise.get(0).getMuscles();

      muscleDtoGrid.setItems(muscleFacade.getMusclesByName(muscleNames));
    });

  }

  private void listMuscles() {
    muscleDtoGrid.setItems(muscleFacade.getMusclesByName(Arrays.asList("*")));
  }

  private void listExercises() {
    exerciseDtoGrid.setItems(exerciseFacade.getExercises(Arrays.asList("*")));
  }

}