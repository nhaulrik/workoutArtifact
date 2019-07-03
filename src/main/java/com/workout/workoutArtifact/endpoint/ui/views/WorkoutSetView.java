package com.workout.workoutArtifact.endpoint.ui.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import com.workout.workoutArtifact.endpoint.dto.WorkoutSetDto;
import com.workout.workoutArtifact.endpoint.dto.validator.WorkoutSetDtoValidator;
import com.workout.workoutArtifact.endpoint.facade.WorkoutSetFacade;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Route(value = "workoutsetcontroller")
@PageTitle("Workout Set Controller")
@Component
@UIScope
public class WorkoutSetView extends VerticalLayout {

  private final WorkoutSetFacade workoutSetFacade;

  final Grid<WorkoutSetDto> workoutSetDtoGrid;

  @Autowired
  public WorkoutSetView(WorkoutSetFacade workoutSetFacade,
      WorkoutSetEditor workoutSetEditor,
      WorkoutSetDtoValidator workoutSetDtoValidator) {
    this.workoutSetFacade = workoutSetFacade;
    this.workoutSetDtoGrid = new Grid<>(WorkoutSetDto.class);

    // build layout
    add(workoutSetDtoGrid, workoutSetEditor);
    workoutSetDtoGrid.setHeight("300px");
    workoutSetDtoGrid.setColumns("exerciseName", "weight", "repetitions", "single", "repetitionMaximum");
    workoutSetDtoGrid.addComponentColumn(item -> createRemoveButton(workoutSetDtoGrid, item))
        .setHeader("Actions");

    initializeEditors(workoutSetEditor);

    // Instantiate and edit new WorkoutSetDto
    workoutSetEditor.editWorkoutSet(WorkoutSetDto.builder()
    .exerciseName("Type Exercise Here")
        .build());

    // Listen changes made by the editor, refresh data from backend
    workoutSetEditor.setChangeHandler(() -> { listWorkoutSets(); });

    listWorkoutSets();
  }

  private void initializeEditors(WorkoutSetEditor workoutSetEditor) {

    workoutSetDtoGrid.addItemClickListener(event -> {
      workoutSetEditor.editWorkoutSet(event.getItem());
    });
  }

  private void listWorkoutSets() {
    List<WorkoutSetDto> workoutSets = workoutSetFacade.getWorkoutSets();
    workoutSetDtoGrid.setItems(workoutSets);
  }

  private Button createRemoveButton(Grid<WorkoutSetDto> grid, WorkoutSetDto workoutSetDto) {
    Button button = new Button("Remove", clickEvent -> {
      ListDataProvider<WorkoutSetDto> dataProvider = (ListDataProvider<WorkoutSetDto>) grid
          .getDataProvider();
      dataProvider.getItems().remove(workoutSetDto);
      dataProvider.refreshAll();
      workoutSetFacade.removeWorkoutSet(workoutSetDto);
    });
    return button;
  }
}
