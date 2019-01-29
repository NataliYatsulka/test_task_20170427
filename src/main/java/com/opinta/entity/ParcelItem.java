package com.opinta.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

public class ParcelItem {

  @Id
  @GeneratedValue
  private long id;
  private String name;
  private long quantity;
  private float weight;
  private BigDecimal price;
}
