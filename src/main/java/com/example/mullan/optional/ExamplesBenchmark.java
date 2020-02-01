package com.example.mullan.optional;

import com.example.mullan.optional.data.Address;
import com.example.mullan.optional.data.Merchant;

public class ExamplesBenchmark {
  private static final long ONE_BILLION = 1_000_000;
  private static final String MILPITAS = "milpitas";

  private static final Merchant MERCHANT = Merchant.builder()
      .address(
          Address.builder()
              .city(MILPITAS)
              .build()
      )
      .build();

  public static void main(final String[] args) {
    final Long startTimeMillis = System.currentTimeMillis();
    doNullChecks();
    final Long completedNullChecksMillis = System.currentTimeMillis();
    doOptionalChaining();
    final Long completedOptionalChainingMillis = System.currentTimeMillis();

    System.out.println(String.format("Completed null checks in %s millis", completedNullChecksMillis - startTimeMillis));
    System.out.println(String.format("Completed optional chaining in %s millis", completedOptionalChainingMillis - completedNullChecksMillis));
  }

  private static void doNullChecks() {
    BenchmarkHelper.repeat(ONE_BILLION, () -> Examples.tryParseCityUsingNestedNullChecks(MERCHANT));
  }

  private static void doOptionalChaining() {
    BenchmarkHelper.repeat(ONE_BILLION, () -> Examples.tryParseCityUsingOptionalChaining(MERCHANT));
  }

  private static void startTimer() {
    System.currentTimeMillis();
  }
}
