package com.workout.workoutArtifact.specification;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MatchAllSpecificationTest {
    @Test
    public void matchesAll() {
        MatchAllSpecification matchAllSpecification = new MatchAllSpecification();
        assertThat(matchAllSpecification.isSatisfiedBy(null), is(true));
    }
}
