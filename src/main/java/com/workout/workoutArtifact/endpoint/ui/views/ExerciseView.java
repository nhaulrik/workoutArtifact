package com.workout.workoutArtifact.endpoint.ui.views;

import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.workout.workoutArtifact.endpoint.facade.ExerciseFacade;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "exercises")
@PageTitle("Exercises overview")
public class ExerciseView {

  ExerciseFacade exerciseFacade;

  @Autowired
  public ExerciseView(ExerciseFacade exerciseFacade) {
    this.exerciseFacade = exerciseFacade;


  }



}
