package com.example.mullan.optional.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class City {
  private String name;

  private Long population;
}
