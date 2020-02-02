package com.example.mullan.optional;

import com.example.mullan.optional.data.Address;
import com.example.mullan.optional.data.Merchant;
import com.sun.istack.internal.Nullable;

import java.util.Optional;

class Examples {
  static String tryParseCityUsingNestedNullChecksAndReassignment(@Nullable final Merchant merchant) {
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

  static String tryParseCityUsingNestedNullChecksWithoutReassignment(@Nullable final Merchant merchant) {
    final boolean hasCity = merchant != null &&
                            merchant.getAddress() != null &&
                            merchant.getAddress().getCity() != null;

    return hasCity ?
        merchant.getAddress().getCity().toUpperCase() :
        null;
  }

  static String tryParseCityUsingOptionalChaining(@Nullable  final Merchant merchant) {
    return Optional.ofNullable(merchant)
        .map(Merchant::getAddress)
        .map(Address::getCity)
        .map(String::toUpperCase)
        .orElse(null);
  }

  static String __unsafeTryParseCityWithOptionalChaining(@Nullable  final Merchant merchant) {
    return Optional.of(merchant)
        .map(Merchant::getAddress)
        .map(Address::getCity)
        .map(String::toUpperCase)
        .orElse(null);
  }

  private Examples() {}
}
