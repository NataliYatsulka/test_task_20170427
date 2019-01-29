package com.opinta.service;

import com.opinta.dto.ParcelDto;
import com.opinta.entity.Parcel;

import java.util.List;

public interface ParcelService {

  List<Parcel> getAllEntities();

  Parcel getEntityById(long id);

  Parcel saveEntity(Parcel parcel);

  List<ParcelDto> getAll();

  ParcelDto getById(long id);

  ParcelDto save(ParcelDto parcelDto);

  ParcelDto update(long id, ParcelDto parcelDto);

  boolean delete(long id);
}
