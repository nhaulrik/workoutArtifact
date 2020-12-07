package controller;

import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import dto.GraphQLRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GraphQLController {

  private final GraphQL graphQL;

  @CrossOrigin(origins = {"http://localhost:4200", "http://46.101.126.222:4200"})
  @RequestMapping(value = "/graphql", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @ResponseBody
  public Map<String, Object> graphql(
      @RequestBody GraphQLRequest requestBody,
      GraphQLRequest requestParams,
      HttpServletRequest raw) {

    String query = requestParams.getQuery() == null ? requestBody.getQuery() : requestParams.getQuery();
    String operationName = requestParams.getOperationName() == null ? requestBody.getOperationName() : requestParams.getOperationName();
    Map<String, Object> variables = requestParams.getVariables() == null ? requestBody.getVariables() : requestParams.getVariables();

    ExecutionResult executionResult = graphQL.execute(ExecutionInput.newExecutionInput()
        .query(query)
        .operationName(operationName)
        .variables(variables)
        .context(raw.getUserPrincipal())
        .build()
    );
    return executionResult.toSpecification();
  }

}
