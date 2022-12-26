package com.ebaykorea.payback.util.support;

import java.util.*;
import java.util.stream.Stream;

import static java.util.Map.entry;
import static java.util.stream.Collectors.toUnmodifiableList;

public class Conditioner<T> {
  private final List<Map.Entry<T, Class<?>[][]>> instances;

  private Conditioner(final List<Map.Entry<T, Class<?>[][]>> instances) {
    this.instances = instances;
  }

  public static <T> Conditioner<T> of(final Collection<T> instances) {
    return new Conditioner<>(parsing(instances));
  }

  public static <T> ConditionerBuilder<T> builder() {
    return new ConditionerBuilder<>();
  }

  private static <T> List<Map.Entry<T, Class<?>[][]>> parsing(final Collection<T> instances) {
    return instances.stream()
        .map(instance -> entry(instance, getAnnotationsByType(instance)))
        .collect(toUnmodifiableList());
  }

  private static <T> Class<?>[][] getAnnotationsByType(T instance) {
    return Arrays.stream(instance.getClass().getAnnotationsByType(Precondition.class))
        .map(Precondition::value)
        .toArray(Class[][]::new);
  }

  /**
   * instances 중에 대상 클래스에 부합하는 조건을 가진 instance 를 하나 찾습니다.
   *
   * @param targetClass 조건을 시험할 대상 클래스
   * @return 대상 클래스에 부합하는 조건을 가진 instance
   */
  public Optional<T> findAny(final Class<?> targetClass) {
    return stream(targetClass).findFirst();
  }

  /**
   * instances 중에 대상에 부합하는 조건을 가진 instance 를 하나 찾습니다.
   *
   * @param target 조건을 시험할 대상
   * @return 대상에 부합하는 조건을 가진 instance
   */
  public T get(final Object target) {
    return findAny(target.getClass()).orElseThrow();
  }

  private Stream<T> stream(final Class<?> targetClass) {
    return instances.stream()
        .filter(instance -> {
          final var preconditions = instance.getValue();
          // 조건이 없다면 항상 채택
          if (preconditions.length == 0) {
            return true;
          }
          for (Class<?>[] precondition : preconditions) {
            var b = true;
            for (Class<?> preconditionClass : precondition) {
              if (!preconditionClass.isAssignableFrom(targetClass)) {
                b = false;
                break;
              }
            }
            if (b) {
              return true;
            }
          }
          return false;
        })
        .map(Map.Entry::getKey);
  }

  public static class ConditionerBuilder<T> {
    private final List<Map.Entry<T, Class<?>[][]>> instances;

    public ConditionerBuilder() {
      this.instances = new ArrayList<>();
    }

    public ConditionerBuilder<T> add(Class<?>[][] precondition, T instance) {
      instances.add(entry(instance, precondition));
      return this;
    }

    public ConditionerBuilder<T> add(Class<?>[] precondition, T instance) {
      return add(new Class<?>[][]{precondition}, instance);
    }

    public ConditionerBuilder<T> add(Class<?> precondition, T instance) {
      return add(new Class<?>[]{precondition}, instance);
    }

    public Conditioner<T> build() {
      return new Conditioner<>(instances);
    }
  }
}
