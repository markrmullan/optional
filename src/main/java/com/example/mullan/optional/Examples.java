package com.example.mullan.optional;

import com.example.mullan.optional.data.Address;
import com.example.mullan.optional.data.Merchant;

import java.util.Optional;

class Examples {
  static String tryParseCityUsingNestedNullChecks(final Merchant merchant) {
    if (merchant != null) {
      final Address address = merchant.getAddress();

      if (address != null) {
        final String city = address.getCity();

        if (city != null) {
          return city.toUpperCase();
        }
      }
    }

    return null;
  }

  static String tryParseCityUsingOptionalChaining(final Merchant merchant) {
    return Optional.ofNullable(merchant)
        .map(Merchant::getAddress)
        .map(Address::getCity)
        .map(String::toUpperCase)
        .orElse(null);
  }

  static String __unsafeTryParseCityWithOptionalChaining(final Merchant merchant) {
    return Optional.of(merchant)
        .map(Merchant::getAddress)
        .map(Address::getCity)
        .map(String::toUpperCase)
        .orElse(null);
  }
}
