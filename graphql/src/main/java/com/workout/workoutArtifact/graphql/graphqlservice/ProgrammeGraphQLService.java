package com.workout.workoutArtifact.graphql.graphqlservice;

import com.fasterxml.jackson.databind.ser.std.ArraySerializerBase;
import com.workout.workoutArtifact.domain.programme.model.Phase;
import com.workout.workoutArtifact.domain.programme.model.Programme;
import com.workout.workoutArtifact.domain.specification.AbstractSpecification;
import com.workout.workoutArtifact.domain.specification.MatchAllSpecification;
import com.workout.workoutArtifact.graphql.configuration.GraphQLSPQRConfig.GraphQLService;
import com.workout.workoutArtifact.graphql.dto.PhaseDto;
import com.workout.workoutArtifact.graphql.dto.ProgrammeDto;
import com.workout.workoutArtifact.graphql.dto.mapper.PhaseDtoMapper;
import com.workout.workoutArtifact.graphql.dto.mapper.ProgrammeDtoMapper;
import com.workout.workoutArtifact.graphql.fetcher.programme.ProgrammeFetcher;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLQuery;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProgrammeGraphQLService implements GraphQLService {

  private final ProgrammeFetcher programmeFetcher;

  private final ProgrammeDtoMapper programmeDtoMapper;
  private final PhaseDtoMapper phaseDtoMapper;

  @GraphQLQuery(name = "programmes")
  public List<ProgrammeDto> getProgrammes(
      @GraphQLArgument(name = "ids") List<UUID> ids,
      @GraphQLArgument(name = "names") List<String> names
  ) {
    List<AbstractSpecification<Programme>> programmeSpecification = new ArrayList<>();
    if (ids != null) { programmeSpecification.add(new Programme.IdsSpecification(ids));}
    if (names != null) { programmeSpecification.add(new Programme.NamesSpecification(names));}

    AbstractSpecification aggregatedSpecification = programmeSpecification.stream().reduce(AbstractSpecification::and).orElse(new MatchAllSpecification());

    return programmeFetcher.getProgrammes(aggregatedSpecification);
  }

  @GraphQLQuery(name = "phases")
  public List<PhaseDto> getPhases(
      @GraphQLContext ProgrammeDto programmeDto
  ) {
    List<AbstractSpecification<Programme>> phaseSpecifications = new ArrayList<>();
    phaseSpecifications.add(new Programme.IdsSpecification(Arrays.asList(programmeDto.getId())));

    AbstractSpecification aggregatedSpecification = phaseSpecifications.stream().reduce(AbstractSpecification::and).orElse(new MatchAllSpecification());

    List<PhaseDto> phaseDtos = programmeFetcher.getPhases(aggregatedSpecification);
    return phaseDtos;
  }


}
