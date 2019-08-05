package com.workout.workoutArtifact.specification;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AbstractSpecificationTest {
    private AbstractSpecification trueSpecification() {
        return new AbstractSpecification() {
            @Override
            public boolean isSatisfiedBy(Object o) {
                return true;
            }
        };
    }

    private AbstractSpecification falseSpecification() {
        return new AbstractSpecification() {
            @Override
            public boolean isSatisfiedBy(Object o) {
                return false;
            }
        };
    }

    @Test
    public void and() {
        AbstractSpecification specification = trueSpecification();
        AbstractSpecification specification2 = trueSpecification();
        AbstractSpecification<Object> abstractSpecification = specification.and(specification2);
        assertThat(abstractSpecification, instanceOf(AndSpecification.class));
        assertThat(((AndSpecification<Object>)abstractSpecification).getSet(), hasItems(specification, specification2));
    }

    @Test
    public void or() {
        AbstractSpecification specification = trueSpecification();
        AbstractSpecification specification2 = trueSpecification();
        AbstractSpecification<Object> abstractSpecification = specification.or(specification2);
        assertThat(abstractSpecification, instanceOf(OrSpecification.class));
        assertThat(((OrSpecification<Object>)abstractSpecification).getSet(), hasItems(specification, specification2));
    }

    @Test
    public void not() {
        AbstractSpecification specification = trueSpecification();
        AbstractSpecification<Object> notSpecification = specification.not();
        assertThat(notSpecification, instanceOf(NotSpecification.class));
        assertThat(((NotSpecification<Object>)notSpecification).getSpecification(), is(specification));
    }
}
