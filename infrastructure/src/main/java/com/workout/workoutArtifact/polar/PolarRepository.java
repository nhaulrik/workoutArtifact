package com.workout.workoutArtifact.polar;

import com.workout.workoutArtifact.polar.entity.PolarSession;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class PolarRepository {

  @Bean
  public List<PolarSession> getBlockingWorkoutsFromPolar() {

    List<PolarSession> polarSessionList = new ArrayList<>();

    WebClient client = WebClient.builder()
        .baseUrl("https://www.polaraccesslink.com")
        .defaultCookie("Cookie", "AWSALB=ds2uDuO0YZE8bH0PvmjwES0djQLA6xijzB0Q3+esVvggXP2DSFhAnN0zohaqWGt/rFxsvw524RuWU2NcJNP9KTWtHQ2AhN5W2NW2NgIR5z6yZrGbei4qCdymRX4D")
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
    return polarSessionList;
  }


    //@Bean
  public void getWorkoutsFromPolar() {

    List<PolarSession> polarSessionList = new ArrayList<>();

    WebClient client = WebClient.builder()
        .baseUrl("https://www.polaraccesslink.com")
        .defaultCookie("Cookie", "AWSALB=ds2uDuO0YZE8bH0PvmjwES0djQLA6xijzB0Q3+esVvggXP2DSFhAnN0zohaqWGt/rFxsvw524RuWU2NcJNP9KTWtHQ2AhN5W2NW2NgIR5z6yZrGbei4qCdymRX4D")
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .defaultHeader("Authorization", "Bearer cc41c46a7e5ae3bd0d65db19513a1460")
        .defaultUriVariables(Collections.singletonMap("url", "https://www.polaraccesslink.com"))
        .build();

    Mono<ClientResponse> response = client.get()
        .uri(URI.create("https://www.polaraccesslink.com/v3/exercises"))
        .exchange();


      response.subscribe(result -> {

        Mono<PolarSession[]> stringMono = result.bodyToMono(PolarSession[].class);
        stringMono.subscribe(polarSessions -> {
          polarSessionList.addAll(Arrays.asList(polarSessions));
        });
      });
  }



}
