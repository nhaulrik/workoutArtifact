package com.workout.workoutArtifact.endpoint.ui.views;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.converter.StringToBooleanConverter;
import com.vaadin.flow.data.converter.StringToDoubleConverter;
import com.vaadin.flow.data.converter.StringToIntegerConverter;
import com.workout.workoutArtifact.endpoint.dto.WorkoutSetDto;
import com.workout.workoutArtifact.endpoint.facade.WorkoutSetFacade;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WorkoutSetEditor extends VerticalLayout implements KeyNotifier {

  private final WorkoutSetFacade workoutSetFacade;

  //WorkoutSetDto to edit
  private WorkoutSetDto workoutSetDto;

  //Fields to edit
  TextField exerciseName = new TextField("Exercise Name");
  TextField exerciseWeight = new TextField("Weight");
  TextField repetitions = new TextField("Repetitions");
  TextField repetitionMaximum = new TextField("Repetition Max.");
  TextField single = new TextField("Single");

  //Action buttons
  Button save = new Button("Save", VaadinIcon.CHECK.create());
  Button cancel = new Button("Cancel");
  Button delete = new Button("Delete", VaadinIcon.TRASH.create());
  HorizontalLayout actions = new HorizontalLayout(save, cancel, delete);

  Binder<WorkoutSetDto> binder = new Binder<>(WorkoutSetDto.class);
  private ChangeHandler changeHandler;

  @Autowired
  public WorkoutSetEditor(
      WorkoutSetFacade workoutSetFacade) {
    this.workoutSetFacade = workoutSetFacade;

    add(exerciseName, exerciseWeight, repetitions, repetitionMaximum, single, actions);

    //bind using naming convention
    binder.forField(repetitions)
        .withConverter(new StringToIntegerConverter("must be integer"))
        .bind(WorkoutSetDto::getRepetitions, WorkoutSetDto::setRepetitions);

    binder.forField(exerciseWeight)
        .withConverter(new StringToDoubleConverter("must be double"))
        .bind(WorkoutSetDto::getWeight, WorkoutSetDto::setWeight);

    binder.forField(repetitionMaximum)
        .withConverter(new StringToIntegerConverter("must be integer"))
        .bind(WorkoutSetDto::getRepetitionMaximum, WorkoutSetDto::setRepetitionMaximum);

    binder.forField(single)
        .withConverter(new StringToBooleanConverter("must be boolean"))
        .bind(WorkoutSetDto::getSingle, WorkoutSetDto::setSingle);

    binder.bindInstanceFields(this);

    //Configure and style components
    setSpacing(true);

    save.getElement().getThemeList().add("primary");
    delete.getElement().getThemeList().add("error");

    addKeyPressListener(Key.ENTER, e -> saveWorkoutSetDto());

    // wire action buttons to save, delete and reset
    save.addClickListener(e -> saveWorkoutSetDto());
//    delete.addClickListener(e -> delete());
    cancel.addClickListener(e -> editWorkoutSet(workoutSetDto));
    setVisible(false);
  }

  void saveWorkoutSetDto() {
    workoutSetFacade.addWorkoutSet(workoutSetDto);
    changeHandler.onChange();
  }

  public final void editWorkoutSet(WorkoutSetDto ws) {
    if (ws == null) {
      setVisible(false);
      return;
    } else {
      setVisible(true);
      workoutSetDto = ws;
    }

    cancel.setVisible(true);

    // Bind WorkoutSetDto properties to similarly named fields
    // Could also use annotation or "manual binding" or programmatically
    // moving values from fields to entities before saving
    binder.setBean(workoutSetDto);
    // Focus exercise name initially
    exerciseName.focus();
  }

  public interface ChangeHandler {

    void onChange();
  }

  public void setChangeHandler(ChangeHandler h) {
    // ChangeHandler is notified when either save or delete
    // is clicked
    changeHandler = h;
  }

}
