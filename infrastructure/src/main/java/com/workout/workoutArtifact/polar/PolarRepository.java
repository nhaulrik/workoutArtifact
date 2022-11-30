package com.workout.workoutArtifact.polar;

import com.workout.workoutArtifact.mysql.mapper.PolarSessionMapper;
import com.workout.workoutArtifact.polar.entity.PolarSession;
import com.workout.workoutArtifact.session.ExternalSessionRepository;
import com.workout.workoutArtifact.session.Session;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class PolarRepository implements ExternalSessionRepository {

  private final PolarSessionMapper polarSessionMapper;

  public PolarRepository(
      PolarSessionMapper polarSessionMapper) {
    this.polarSessionMapper = polarSessionMapper;
  }

  @Override
  public List<Session> getExternalSessions(UUID userId) {

    List<PolarSession> polarSessionList = new ArrayList<>();

    WebClient client = WebClient.builder()
        .baseUrl("https://www.polaraccesslink.com")
        .defaultCookie("Cookie",
            "AWSALB=ds2uDuO0YZE8bH0PvmjwES0djQLA6xijzB0Q3+esVvggXP2DSFhAnN0zohaqWGt/rFxsvw524RuWU2NcJNP9KTWtHQ2AhN5W2NW2NgIR5z6yZrGbei4qCdymRX4D")
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .defaultHeader("Authorization", "Bearer cc41c46a7e5ae3bd0d65db19513a1460")
        .defaultUriVariables(Collections.singletonMap("url", "https://www.polaraccesslink.com"))
        .build();

    ResponseEntity<List<PolarSession>> responseEntity = client.get()
        .uri(URI.create("https://www.polaraccesslink.com/v3/exercises"))
        .retrieve()
        .toEntityList(PolarSession.class)
        .block();

    polarSessionList.addAll(responseEntity.getBody());

    List<Session> sessions = polarSessionList.stream()
        .map(polarSessionMapper::toDomain)
        .collect(Collectors.toList());

    return sessions;
  }
}
