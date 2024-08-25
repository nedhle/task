package app.service;

import app.controller.request.DeviceRequest;
import app.dto.DeviceDto;
import app.mapper.DeviceMapper;
import app.model.Device;
import app.repository.DeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeviceService {
    private final DeviceRepository deviceRepository;
    private final DeviceMapper deviceMapper;

    public Device addDevice(DeviceRequest deviceRequest) {
        DeviceDto deviceDto = deviceMapper.requestToDto(deviceRequest);
        return deviceRepository.save(deviceMapper.dtoToEntity(deviceDto));
    }
}
