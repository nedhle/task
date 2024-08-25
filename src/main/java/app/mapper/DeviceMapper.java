package app.mapper;

import app.controller.request.DeviceRequest;
import app.dto.DeviceDto;
import app.model.Device;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DeviceMapper {

  DeviceMapper INSTANCE = Mappers.getMapper(DeviceMapper.class);

  Device dtoToEntity(DeviceDto deviceDto);

  DeviceDto requestToDto(DeviceRequest deviceRequest);
}
