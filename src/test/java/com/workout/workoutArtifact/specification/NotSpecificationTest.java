package com.workout.workoutArtifact.specification;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class NotSpecificationTest {
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
    public void negatePositiveResult() {
        AbstractSpecification trueSpecification = trueSpecification();
        NotSpecification notSpecification = new NotSpecification(trueSpecification);
        assertThat(notSpecification.getSpecification(), is(trueSpecification));
        assertThat(notSpecification.isSatisfiedBy(null), is(false));

    }

    @Test
    public void negateNegativeResult() {
        AbstractSpecification falseSpecification = falseSpecification();
        NotSpecification notSpecification = new NotSpecification(falseSpecification);
        assertThat(notSpecification.getSpecification(), is(falseSpecification));
        assertThat(notSpecification.isSatisfiedBy(null), is(true));
    }
}
