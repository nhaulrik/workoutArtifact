package specification;

import lombok.Value;

@Value
public class NotSpecification<T> extends AbstractSpecification<T> {

  private final AbstractSpecification<T> specification;

  public NotSpecification(AbstractSpecification<T> specification) {
    this.specification = specification;
  }

  @Override
  public boolean isSatisfiedBy(T t) {
    return !specification.isSatisfiedBy(t);
  }
}
