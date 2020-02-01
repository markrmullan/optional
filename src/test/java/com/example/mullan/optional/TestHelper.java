package com.example.mullan.optional;

import java.util.stream.LongStream;

public class TestHelper {
  static void repeat(final long times, final Runnable action) {
    LongStream.range(0, times)
        .forEach(i -> action.run());
  }
}
