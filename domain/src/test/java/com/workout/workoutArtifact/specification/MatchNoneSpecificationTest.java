package com.workout.workoutArtifact.specification;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MatchNoneSpecificationTest {
    @Test
    public void matchesNone() {
        MatchNoneSpecification matchNoneSpecification = new MatchNoneSpecification();
        assertThat(matchNoneSpecification.isSatisfiedBy(null), is(false));
    }
}
