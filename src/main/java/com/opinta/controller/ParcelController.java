package com.opinta.controller;

import com.opinta.dto.ParcelDto;
import com.opinta.service.ParcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

public class ParcelController {
  private ParcelService parcelService;

  @Autowired
  public ParcelController(ParcelService parcelService) {
    this.parcelService = parcelService;
  }

  @GetMapping
  @ResponseStatus(OK)
  public List<ParcelDto> getParcel() {
    return parcelService.getAll();
  }

  @GetMapping("{id}")
  public ResponseEntity<?> getParcel(@PathVariable("id") long id) {
    ParcelDto parcelDto = parcelService.getById(id);
    if (parcelDto == null) {
      return new ResponseEntity<>(format("No Parcel found for ID %d", id), NOT_FOUND);
    }
    return new ResponseEntity<>(parcelDto, OK);
  }

  @PostMapping
  @ResponseStatus(OK)
  public ResponseEntity<?> createParcel(@RequestBody ParcelDto parcelDto) {
    parcelDto = parcelService.save(parcelDto);
    if (parcelDto == null) {
      return new ResponseEntity<>("Failed to create new Parcel using given data.", BAD_REQUEST);
    }
    return new ResponseEntity<>(parcelDto, OK);
  }

  @PutMapping("{id}")
  public ResponseEntity<?> updateParcel(@PathVariable long id, @RequestBody ParcelDto parcelDto) {
    parcelDto = parcelService.update(id, parcelDto);
    if (parcelDto == null) {
      return new ResponseEntity<>(format("No Parcel found for ID %d", id), NOT_FOUND);
    }
    return new ResponseEntity<>(parcelDto, OK);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<?> deleteParcel(@PathVariable long id) {
    if (!parcelService.delete(id)) {
      return new ResponseEntity<>(format("No Parcel found for ID %d", id), NOT_FOUND);
    }
    return new ResponseEntity<>(OK);
  }
}
