package com.ebaykorea.payback;

import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

public class DependencyTest {

  @Test
  @DisplayName("core 패키지는 api와 infra 패키지를 참조 할 수 없다")
  void coreDependencyTest() {
    final var importedClasses = new ClassFileImporter().importPackages("com.ebaykorea.payback");

    final var rule = noClasses().that().resideInAPackage("..core..")
            .should().dependOnClassesThat().resideInAnyPackage("..api..", "..infrastructure..");

    rule.check(importedClasses);
  }

}
