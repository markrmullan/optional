package com.example.mullan.optional;

import com.example.mullan.optional.data.Address;
import com.example.mullan.optional.data.Merchant;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;

public class ExamplesBenchmark {
  private static final long ONE_BILLION = 100_000_000;
  private static final String MILPITAS = "milpitas";

  private static final Merchant MERCHANT = Merchant.builder()
      .address(
          Address.builder()
              .city(MILPITAS)
              .build()
      )
      .build();

  private static Long prevMillis;
  private static Long prevGCCollectionTime;

  public static void main(final String[] args) {
    init();


    doNullChecksWithoutReassignment();
    recordEvents("doNullChecksWithoutReassignment");

    doNullChecks();
    recordEvents("doNullChecks");

    doOptionalChaining();
    recordEvents("doOptionalChaining");

  }

  private static void init() {
    prevMillis = System.currentTimeMillis();
    prevGCCollectionTime = getCurrentGCCollectionTime();
  }

  private static void recordEvents(final String operationName) {
    final Long now = System.currentTimeMillis();
    final Long gc = getCurrentGCCollectionTime();

    System.out.println(String.format("Previous operation %s took %s millis", operationName, now - prevMillis));
    prevMillis = now;
    System.out.println(String.format("Previous operation %s result in %s millis of GC", operationName, gc - prevGCCollectionTime));
    prevGCCollectionTime = gc;
  }

  private static void doNullChecks() {
    for (int i = 0; i < ONE_BILLION; i++) {
      Examples.tryParseCityUsingNestedNullChecksAndReassignment(MERCHANT);
    }
  }

  private static void doNullChecksWithoutReassignment() {
    for (int i = 0; i < ONE_BILLION; i++) {
      Examples.tryParseCityUsingNestedNullChecksWithoutReassignment(MERCHANT);
    }
  }

  private static void doOptionalChaining() {
    for (int i = 0; i < ONE_BILLION; i++) {
      Examples.tryParseCityUsingOptionalChaining(MERCHANT);
    }
  }

  private static Long getCurrentGCCollectionTime() {
    return ManagementFactory
        .getGarbageCollectorMXBeans()
        .stream()
        .map(GarbageCollectorMXBean::getCollectionTime)
        .reduce(0L, Long::sum);
  }
}
