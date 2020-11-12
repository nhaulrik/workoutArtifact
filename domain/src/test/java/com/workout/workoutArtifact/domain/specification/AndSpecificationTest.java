package com.workout.workoutArtifact.domain.specification;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AndSpecificationTest {
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
    public void twoTrueSpecifications() {
        AbstractSpecification trueSpecification = trueSpecification();
        AbstractSpecification trueSpecification2 = trueSpecification();
        AndSpecification<Object> andSpecification = new AndSpecification<>(trueSpecification, trueSpecification2);
        assertThat(andSpecification.getSet(), Matchers.hasItems(trueSpecification, trueSpecification2));
        assertThat(andSpecification.isSatisfiedBy(null), is(true));
    }

    @Test
    public void oneTrueOneFalseSpecification() {
        AbstractSpecification trueSpecification = trueSpecification();
        AbstractSpecification falseSpecification = falseSpecification();
        AndSpecification<Object> andSpecification = new AndSpecification<>(trueSpecification, falseSpecification);
        assertThat(andSpecification.getSet(), Matchers.hasItems(trueSpecification, falseSpecification));
        assertThat(andSpecification.isSatisfiedBy(null), is(false));
    }

    @Test
    public void twoFalseSpecifications() {
        AbstractSpecification falseSpecification = falseSpecification();
        AbstractSpecification falseSpecification2 = falseSpecification();
        AndSpecification<Object> andSpecification = new AndSpecification<>(falseSpecification, falseSpecification2);
        assertThat(andSpecification.getSet(), Matchers.hasItems(falseSpecification, falseSpecification2));
        assertThat(andSpecification.isSatisfiedBy(null), is(false));
    }

    @Test
    public void chainingAndSpecification() {
        AbstractSpecification trueSpecification = trueSpecification();
        AbstractSpecification trueSpecification2 = trueSpecification();
        AbstractSpecification trueSpecification3 = trueSpecification();
        AndSpecification<Object> andSpecification = new AndSpecification<>(trueSpecification, trueSpecification2);
        andSpecification.and(trueSpecification3);
        assertThat(andSpecification.getSet(), Matchers.hasItems(trueSpecification, trueSpecification2, trueSpecification3));
    }
}
