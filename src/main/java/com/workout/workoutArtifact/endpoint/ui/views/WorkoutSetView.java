package com.workout.workoutArtifact.endpoint.ui.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
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
  final TextField filter;

  @Autowired
  public WorkoutSetView(WorkoutSetFacade workoutSetFacade,
      WorkoutSetEditor workoutSetEditor) {
    this.workoutSetFacade = workoutSetFacade;
    this.workoutSetDtoGrid = new Grid<>(WorkoutSetDto.class);
    this.filter = new TextField();

    // build layout
    HorizontalLayout actions = new HorizontalLayout(filter);
    add(actions, workoutSetDtoGrid, workoutSetEditor);
    workoutSetDtoGrid.setHeight("300px");
    workoutSetDtoGrid.setColumns("exerciseName", "weight", "repetitions", "single", "repetitionMaximum");
    workoutSetDtoGrid.addComponentColumn(item -> createRemoveButton(workoutSetDtoGrid, item))
        .setHeader("Actions");

    filter.setPlaceholder("Filter by something");

    // Hook logic to components

    // Replace listing with filtered content when user changes filter
    filter.setValueChangeMode(ValueChangeMode.EAGER);
    filter.addValueChangeListener(e -> workoutSetFacade.getWorkoutSets()); //use e.getValue() as param.

    // Connect selected WorkoutSet to editor or hide if none is selected
    workoutSetDtoGrid.asSingleSelect().addValueChangeListener(e -> {
      workoutSetEditor.editWorkoutSet(e.getValue());
    });

    // Instantiate and edit new WorkoutSetDto
    workoutSetEditor.editWorkoutSet(new WorkoutSetDto("Type Exercise Here", 0, 0, false, 0));

    // Listen changes made by the editor, refresh data from backend
    workoutSetEditor.setChangeHandler(() -> { listWorkoutSets(); });

    listWorkoutSets();
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
