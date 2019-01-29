package com.opinta.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Parcel {
  @Id
  @GeneratedValue
  private long id;

  private float weight;
  private float length;
  private float width;
  private float height;
  private BigDecimal declaredPrice;
  private BigDecimal price;

  @OneToMany
  @JoinColumn(name = "parsel_id")
  private List<ParcelItem> parcelItems = new ArrayList<>();

  public Parcel(float weight, float length, float width, float height, BigDecimal declaredPrice, BigDecimal price) {
    this.weight = weight;
    this.length = length;
    this.width = width;
    this.height = height;
    this.declaredPrice = declaredPrice;
    this.price = price;
  }
}
