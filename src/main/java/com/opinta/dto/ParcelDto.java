package com.opinta.dto;

import com.opinta.entity.ParcelItem;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ParcelDto {

  private long id;
  private float weight;
  private float length;
  private float width;
  private float height;
  private BigDecimal declaredPrice;
  private BigDecimal price;

  private List<ParcelItem> parcelItems = new ArrayList<>();
}
