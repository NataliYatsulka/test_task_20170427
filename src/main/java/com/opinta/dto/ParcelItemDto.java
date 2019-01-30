package com.opinta.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ParcelItemDto {
  private long id;
  private String name;
  private long quantity;
  private float weight;
  private BigDecimal price;
}
