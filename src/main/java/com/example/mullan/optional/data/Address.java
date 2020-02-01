package com.example.mullan.optional.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Address {
  private String address1;

  private String address2;

  private String address3;

  private String city;

  private String state;
}
