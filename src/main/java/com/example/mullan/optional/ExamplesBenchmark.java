package com.example.mullan.optional;

import com.example.mullan.optional.data.Address;
import com.example.mullan.optional.data.City;
import com.example.mullan.optional.data.Merchant;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.stream.LongStream;

public class ExamplesBenchmark {
  private static final long ONE_MILLION = 1_000_000;
  private static final long TEN_MILLION = 10_000_000;
  private static final long ONE_HUNDRED_MILLION = 100_000_000;
  private static final long ONE_BILLION = 1_000_000_000;
  private static final String MILPITAS = "milpitas";

  private static final Merchant MERCHANT = Merchant.builder()
      .address(
          Address.builder()
              .city(
                  City.builder()
                      .name(MILPITAS)
                      .build()
              )
              .build()
      )
      .build();

  private static Long prevMillis;
  private static Long prevGCCollectionTime;

  /**
   * The order in which these methods are invoked might influence runtime-- anecdotally, on my local machine, I consistently
   * found that the first method invoked had a longer runtime that subsequent methods that were called. e.g., if I called
   * {@link ExamplesBenchmark#doNullChecks()} before {@link ExamplesBenchmark#doNullChecksWithoutReassignment()}, I would consistently
   * find that it took {@link ExamplesBenchmark#doNullChecks()} longer to run. However, if the order in which the functions were called
   * was reversed, I found the opposite to be true, and {@link ExamplesBenchmark#doNullChecksWithoutReassignment()} would
   * purportedly run faster. Suffice to say, take this all with a grain of salt, and YMMV depending on the machine you are running
   * this code on.
   *
   * @param args ignored
   */
  public static void main(final String[] args) {
    init();

    doOptionalChaining();
    logTimeElapsed("doOptionalChaining");

    doOptionalChaining();
    logTimeElapsed("doOptionalChaining");

    doOptionalChaining();
    logTimeElapsed("doOptionalChaining");

    //doNullChecksWithoutReassignment();
    //logTimeElapsed("doNullChecksWithoutReassignment");
    //
    //doNullChecksWithoutReassignment();
    //logTimeElapsed("doNullChecksWithoutReassignment");
    //
    //doNullChecksWithoutReassignment();
    //logTimeElapsed("doNullChecksWithoutReassignment");

    //doNullChecks();
    //logTimeElapsed("doNullChecks");
    //
    //doNullChecks();
    //logTimeElapsed("doNullChecks");
    //
    //doNullChecks();
    //logTimeElapsed("doNullChecks");
  }

  private static void init() {
    prevMillis = System.currentTimeMillis();
    prevGCCollectionTime = getCurrentGCCollectionTime();
  }

  private static void logTimeElapsed(final String operationName) {
    final Long now = System.currentTimeMillis();
    final Long gc = getCurrentGCCollectionTime();

    System.out.println(String.format("Previous operation %s took %s millis", operationName, now - prevMillis));
    prevMillis = now;
    System.out.println(String.format("Previous operation %s result in %s millis of GC", operationName, gc - prevGCCollectionTime));
    prevGCCollectionTime = gc;
  }

  private static void repeat(final long times, final Runnable action) {
    LongStream.range(0L, times)
        .forEach(i -> action.run());
  }

  private static void doNullChecks() {
    repeat(ONE_BILLION,
        () -> Examples.tryParseCityUsingNestedNullChecksAndReassignment(MERCHANT));
  }

  private static void doNullChecksWithoutReassignment() {
    repeat(ONE_BILLION,
        () -> Examples.tryParseCityUsingNestedNullChecksWithoutReassignment(MERCHANT));
  }

  private static void doOptionalChaining() {
    repeat(ONE_BILLION,
        () -> Examples.tryParseCityUsingOptionalChaining(MERCHANT));
  }

  private static Long getCurrentGCCollectionTime() {
    return ManagementFactory
        .getGarbageCollectorMXBeans()
        .stream()
        .map(GarbageCollectorMXBean::getCollectionTime)
        .reduce(0L, Long::sum);
  }
}
