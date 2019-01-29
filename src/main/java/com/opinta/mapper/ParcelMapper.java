package com.opinta.mapper;

import com.opinta.dto.ParcelDto;
import com.opinta.entity.Parcel;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface ParcelMapper extends BaseMapper<ParcelDto, Parcel> {
}