package com.workout.workoutArtifact.endpoint.ui.views;

import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import com.workout.workoutArtifact.domain.model.WorkoutSet;
import com.workout.workoutArtifact.endpoint.dto.WorkoutSetDto;
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
      WorkoutSetEditor workoutSetEditor) {
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
    workoutSetEditor.editWorkoutSet(new WorkoutSetDto("Type Exercise Here", 0, "0", false, 0));

    // Listen changes made by the editor, refresh data from backend
    workoutSetEditor.setChangeHandler(() -> { listWorkoutSets(); });

    listWorkoutSets();
  }

  private void initializeEditors(WorkoutSetEditor workoutSetEditor) {

    Binder<WorkoutSetDto> binder = new Binder<>(WorkoutSetDto.class);

    workoutSetDtoGrid.getEditor().setBinder(binder);

    TextField exerciseNameTextfield = new TextField();
    TextField weightTextField = new TextField();

    binder.bind(exerciseNameTextfield, "exerciseName");
    binder.bind(weightTextField, "weight");
    workoutSetDtoGrid.getColumnByKey("exerciseName").setEditorComponent(exerciseNameTextfield);
    workoutSetDtoGrid.getColumnByKey("weight").setEditorComponent(weightTextField);

    workoutSetDtoGrid.addItemClickListener(event -> {
      workoutSetDtoGrid.getEditor().editItem(event.getItem());
      exerciseNameTextfield.focus();
    });

    workoutSetDtoGrid.addFocusListener(event -> {
      if (workoutSetDtoGrid.getEditor().isOpen()) {
        WorkoutSetDto workoutSetDto = workoutSetDtoGrid.getEditor().getItem();
        workoutSetFacade.addWorkoutSet(workoutSetDto);
        listWorkoutSets();
      }
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
