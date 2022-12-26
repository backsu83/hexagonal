package com.ebaykorea.payback.util.support;

import java.lang.annotation.*;


/**
 * {@link Conditioner} 에 의해 분류될 조건 클래스를 표시합니다.
 *
 * 하나의 Precondition 에 여러 클래스를 조건 클래스로 표시할 수 있으며 이 경우 AND 조건으로 판단합니다.
 * 여러개의 Precondition 을 표시할 수 있으며 이 경우 OR 조건으로 판단합니다.
 *
 * ex)
 * <pre>
 * {@code
 * @Precondition({ClassA.class, InterfaceB.class})
 * @Precondition(ClassC.class)
 * public class SomeValidator {}
 * }
 * </pre>
 *
 * 1. ClassA 이면서 InterfaceB 이거나
 * 2. ClassC 인 경우
 */
@Repeatable(Precondition.Preconditions.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Precondition {
  Class<?>[] value();

  @Target(ElementType.TYPE)
  @Retention(RetentionPolicy.RUNTIME)
  @Inherited
  @interface Preconditions {
    Precondition[] value();
  }
}
