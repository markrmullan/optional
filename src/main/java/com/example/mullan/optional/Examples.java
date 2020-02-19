package com.example.mullan.optional;

import com.example.mullan.optional.data.Address;
import com.example.mullan.optional.data.City;
import com.example.mullan.optional.data.Merchant;
import com.sun.istack.internal.Nullable;

import java.util.Optional;

class Examples {
  static String tryParseCityUsingNestedNullChecksAndReassignment(@Nullable final Merchant merchant) {
    if (merchant != null) {
      final Address address = merchant.getAddress();

      if (address != null) {
        final City city = address.getCity();

        if (city != null) {
          final String cityName = city.getName();

          if (cityName != null) {
            return cityName.toUpperCase();
          }
        }
      }
    }

    return null;
  }

  static String tryParseCityUsingNestedNullChecksWithoutReassignment(@Nullable final Merchant merchant) {
    final boolean hasCity = merchant != null &&
                            merchant.getAddress() != null &&
                            merchant.getAddress().getCity() != null &&
                            merchant.getAddress().getCity().getName() != null;

    return hasCity ?
        merchant.getAddress().getCity().getName().toUpperCase() :
        null;
  }

  static String tryParseCityUsingOptionalChaining(@Nullable final Merchant merchant) {
    return Optional.ofNullable(merchant)
        .map(Merchant::getAddress)
        .map(Address::getCity)
        .map(City::getName)
        .map(String::toUpperCase)
        .orElse(null);
  }

  /**
   * There are 3 ways to instantiate an {@link Optional}:
   * - {@link Optional#empty()},
   * - {@link Optional#of(Object)}, OR
   * - {@link Optional#ofNullable(Object)}
   *
   * This method is meant to illustrate that {@link Optional#of(Object)} is only safe to use when you are *certain* that
   * specified value is non-null. ref ExamplesTest.java for what happens if this is not the case.
   */
  static String __unsafeTryParseCityWithOptionalChaining(@Nullable final Merchant merchant) {
    return Optional.of(merchant)
        .map(Merchant::getAddress)
        .map(Address::getCity)
        .map(City::getName)
        .map(String::toUpperCase)
        .orElse(null);
  }

  private Examples() {}
}
