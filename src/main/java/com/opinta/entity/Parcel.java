package com.opinta.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class Parcel {
  @Id
  @GeneratedValue @Getter @Setter
  private long id;
@Getter @Setter
  private float weight;
  @Getter @Setter
  private float length;
  @Getter @Setter
  private float width;
  @Getter @Setter
  private float height;
  @Getter @Setter
  private BigDecimal declaredPrice;
  @Getter @Setter
  private BigDecimal price;
  @Getter @Setter
  private Shipment shipment;

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
