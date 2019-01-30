package com.opinta.service;

import java.math.BigDecimal;
import java.util.List;

import com.opinta.dto.ShipmentDto;
import com.opinta.entity.Shipment;

public interface ShipmentService {

    List<Shipment> getAllEntities();

    Shipment getEntityById(long id);

    Shipment saveEntity(Shipment shipment);
    
    List<ShipmentDto> getAll();

    List<ShipmentDto> getAllByClientId(long clientId);
    
    ShipmentDto getById(long id);
    
    ShipmentDto save(ShipmentDto shipmentDto);
    
    ShipmentDto update(long id, ShipmentDto shipmentDto);
    
    boolean delete(long id);

    BigDecimal calculateAll(Shipment shipment);

    float getWeight(Shipment shipment);

    BigDecimal getDeclaredPrice(Shipment shipment);
}
