package com.opinta.mapper;

import com.opinta.dto.ParcelItemDto;
import com.opinta.entity.ParcelItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ParcelItemMapper extends BaseMapper<ParcelItemDto, ParcelItem> {
}
