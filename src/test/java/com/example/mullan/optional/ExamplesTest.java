package com.example.mullan.optional;

import com.example.mullan.optional.data.Address;
import com.example.mullan.optional.data.City;
import com.example.mullan.optional.data.Merchant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

class ExamplesTest {
  @ParameterizedTest
  @MethodSource("cityParsingFunctions")
  void testTryParseCity(final Function<Merchant, String> cityParser) {
    Assertions.assertNull(cityParser.apply(null));

    Assertions.assertNull(cityParser.apply(
        Merchant.builder().build())
    );

    Assertions.assertNull(cityParser.apply(
        Merchant.builder()
            .address(Address.builder().build())
            .build()
    ));

    Assertions.assertEquals("TEMPE",
        cityParser.apply(
            Merchant.builder()
                .address(Address.builder()
                    .city(City.builder()
                        .name("tempe")
                        .build())
                    .build())
                .build()
        )
    );
  }

  private static List<Function<Merchant, String>> cityParsingFunctions() {
    return Arrays.asList(
        Examples::tryParseCityUsingNestedNullChecksAndReassignment,
        Examples::tryParseCityUsingNestedNullChecksWithoutReassignment,
        Examples::tryParseCityUsingOptionalChaining
    );
  }

  @Test
  void testUnsafeTryParseCity() {
    Assertions.assertThrows(NullPointerException.class,
        () -> Examples.__unsafeTryParseCityWithOptionalChaining(null));
  }
}
