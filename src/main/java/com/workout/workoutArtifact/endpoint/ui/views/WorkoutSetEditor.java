package com.workout.workoutArtifact.endpoint.ui.views;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.converter.StringToDoubleConverter;
import com.vaadin.flow.data.converter.StringToIntegerConverter;
import com.vaadin.flow.spring.annotation.UIScope;
import com.workout.workoutArtifact.endpoint.dto.ExerciseDto;
import com.workout.workoutArtifact.endpoint.dto.WorkoutSetDto;
import com.workout.workoutArtifact.endpoint.dto.validator.WorkoutSetDtoValidator;
import com.workout.workoutArtifact.endpoint.facade.ExerciseFacade;
import com.workout.workoutArtifact.endpoint.facade.WorkoutSetFacade;
import com.workout.workoutArtifact.specification.MatchAllSpecification;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@UIScope
public class WorkoutSetEditor extends VerticalLayout implements KeyNotifier {

  private final WorkoutSetFacade workoutSetFacade;
  private final ExerciseFacade exerciseFacade;
  private final WorkoutSetDtoValidator workoutSetDtoValidator;

  //WorkoutSetDto to edit
  private WorkoutSetDto workoutSetDto;

  //Fields to edit
  TextField exerciseWeight = new TextField("Weight");
  TextField repetitions = new TextField("Repetitions");
  TextField repetitionMaximum = new TextField("Repetition Max.");
  Checkbox singleCheckbox = new Checkbox("Single");

  //Action buttons
  Button save = new Button("Save", VaadinIcon.CHECK.create());
  Button cancel = new Button("Cancel");
  Button delete = new Button("Delete", VaadinIcon.TRASH.create());
  HorizontalLayout actions = new HorizontalLayout(save, cancel, delete);

  Binder<WorkoutSetDto> binder = new Binder<>(WorkoutSetDto.class);
  private ChangeHandler changeHandler;

  @Autowired
  public WorkoutSetEditor(
      WorkoutSetFacade workoutSetFacade,
      ExerciseFacade exerciseFacade,
      WorkoutSetDtoValidator workoutSetDtoValidator) {
    this.workoutSetFacade = workoutSetFacade;
    this.exerciseFacade = exerciseFacade;
    this.workoutSetDtoValidator = workoutSetDtoValidator;

    HorizontalLayout workoutSetEditorFields = new HorizontalLayout(getExerciseNamesComboBox(), exerciseWeight, repetitions, repetitionMaximum, singleCheckbox);
    add(workoutSetEditorFields, actions);

    //bind using naming convention
    binder.forField(repetitions)
        .withConverter(new StringToIntegerConverter("must be integer"))
        .bind(WorkoutSetDto::getRepetitions, WorkoutSetDto::setRepetitions);

    binder.forField(exerciseWeight)
        .withConverter(new StringToDoubleConverter("must be a number"))
        .bind(WorkoutSetDto::getWeight, WorkoutSetDto::setWeight);

    binder.forField(repetitionMaximum)
        .withConverter(new StringToIntegerConverter("must be integer"))
        .bind(WorkoutSetDto::getRepetitionMaximum, WorkoutSetDto::setRepetitionMaximum);

    binder.forField(singleCheckbox)
        .bind(WorkoutSetDto::isSingle, WorkoutSetDto::setSingle);

    binder.bindInstanceFields(this);

    //Configure and style components
    setSpacing(true);

    save.getElement().getThemeList().add("primary");
    delete.getElement().getThemeList().add("error");

    addKeyPressListener(Key.ENTER, e -> saveWorkoutSetDto());

    save.addClickListener(e -> saveWorkoutSetDto());
    cancel.addClickListener(e -> editWorkoutSet(
        WorkoutSetDto.builder()
            .exerciseName("")
            .build()));
    setVisible(true);
  }

  private ComboBox<String> getExerciseNamesComboBox() {
    List<String> exerciseNames = getExerciseName();
    ComboBox<String> comboBoxExercises = new ComboBox<>("Exercises");
    comboBoxExercises.setWidth("300px");
    comboBoxExercises.setItems(exerciseNames);

    comboBoxExercises.addValueChangeListener(event -> {
      if (!event.getSource().isEmpty()) {
        workoutSetDto.setExerciseName(event.getValue());
      }
    });

    return comboBoxExercises;
  }

  private List<String> getExerciseName() {
    List<ExerciseDto> exerciseList = exerciseFacade.getExercises(new MatchAllSpecification());

    return exerciseList.stream()
        .map(e -> e.getName())
        .collect(Collectors.toList());
  }

  void saveWorkoutSetDto() {
    if (workoutSetDtoValidator.validateWorkoutSetDto(workoutSetDto)) {
      workoutSetFacade.addWorkoutSet(workoutSetDto);

      editWorkoutSet(WorkoutSetDto.builder()
          .exerciseName(workoutSetDto.getExerciseName())
          .repetitionMaximum(workoutSetDto.getRepetitionMaximum())
          .single(workoutSetDto.isSingle())
          .build());

      exerciseWeight.focus();
      changeHandler.onChange();
    }
  }

  public final void editWorkoutSet(WorkoutSetDto ws) {

    if (ws != null) {
      workoutSetDto = ws;
    }
    binder.setBean(workoutSetDto);
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
