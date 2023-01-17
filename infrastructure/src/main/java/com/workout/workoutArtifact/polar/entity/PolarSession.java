package com.workout.workoutArtifact.polar.entity;

import lombok.Data;

@Data
public class PolarSession {

  private String id;
  private String upload_time;
  private String polar_user;
  private String device;
  private String start_time;
  private String start_time_utc_offset;
  private String duration;
  private String distance;
  private String sport;
  private String detailed_sport_info;
  private String calories;
  private HeartRate heart_rate;

  @Data
  public static class HeartRate {
    private Integer average;
    private Integer maximum;
  }

}
