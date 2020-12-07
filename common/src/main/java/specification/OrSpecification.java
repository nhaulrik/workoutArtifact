package specification;

import java.util.HashSet;
import java.util.Set;
import lombok.Value;

@Value
public class OrSpecification<T> extends AbstractSpecification<T> {

  private Set<AbstractSpecification<T>> set = new HashSet<>();

  public OrSpecification(AbstractSpecification<T> a, AbstractSpecification<T> b) {
    set.add(a);
    set.add(b);
  }

  public boolean isSatisfiedBy(T t) {
    return set.stream().anyMatch(s -> s.isSatisfiedBy(t));
  }

  @Override
  public AbstractSpecification<T> or(AbstractSpecification<T> s) {
    set.add(s);
    return this;
  }
}
