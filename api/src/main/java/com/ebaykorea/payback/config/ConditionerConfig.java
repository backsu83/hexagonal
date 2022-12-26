package com.ebaykorea.payback.config;

import com.ebaykorea.payback.util.support.Conditioner;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.config.DependencyDescriptor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import java.util.stream.Collectors;

@Configuration
public class ConditionerConfig {

  @Bean
  @Scope("prototype")
  public <T> FactoryBean<Conditioner<T>> conditioner(
      final ApplicationContext applicationContext,
      final InjectionPoint injectionPoint
  ) {
    if (!(injectionPoint instanceof DependencyDescriptor)) {
      return null;
    }

    final var dependencyDescriptor = (DependencyDescriptor) injectionPoint;
    final var generic = dependencyDescriptor.getResolvableType().getGeneric(0);
    final ObjectProvider<T> beanProvider = applicationContext.getBeanProvider(generic);
    final var collect = beanProvider.stream().collect(Collectors.toUnmodifiableList());

    return new FactoryBean<>() {
      @Override
      public Conditioner<T> getObject() throws Exception {
        return Conditioner.of(collect);
      }

      @Override
      public Class<?> getObjectType() {
        return generic.resolve();
      }
    };
  }
}
