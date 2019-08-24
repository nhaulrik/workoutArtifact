package com.workout.workoutArtifact.endpoint.controller;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GraphQLControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Mock
  private GraphQL graphQL;

  @Autowired
  GraphQLController graphQLController;

  @Before
  public void setUp() {
    ReflectionTestUtils.setField(graphQLController, "graphQL", graphQL);
  }

  @Test
  public void graphql() throws Exception {
    Map<String, Object> result = new HashMap<>();
    result.put(RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10));
    result.put(RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10));
    result.put(RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10));

    ExecutionResult executionResult = mock(ExecutionResult.class);

    when(executionResult.toSpecification()).thenReturn(result);
    when(graphQL.execute(any(ExecutionInput.class))).thenReturn(executionResult);

    mockMvc.perform(
        post("/graphql")
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
            .content("{\"query\":\"{test}\",\"variables\":null,\"operationName\":null}"))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString(new ObjectMapper().writeValueAsString(result))));
  }
}