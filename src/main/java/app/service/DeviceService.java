package app.service;

import app.controller.request.DeviceRequest;
import app.dto.DeviceDto;
import app.exception.DeviceNotFoundException;
import app.mapper.DeviceMapper;
import app.model.Device;
import app.repository.DeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeviceService {
    private final DeviceRepository deviceRepository;
    private final DeviceMapper deviceMapper;

    public Device addDevice(DeviceRequest deviceRequest) {
        DeviceDto deviceDto = deviceMapper.requestToDto(deviceRequest);
        return deviceRepository.save(deviceMapper.dtoToEntity(deviceDto));
    }

    public List<Device> listAllDevices() {
        return deviceRepository.findAll();
    }

    public Optional<Device> getDeviceById(Long id) {
        return Optional.ofNullable(deviceRepository.findById(id).orElseThrow(() -> new DeviceNotFoundException(id)));
    }

    public Device updateDevice(Long id, DeviceRequest deviceRequest) {
        DeviceDto deviceDto = deviceMapper.requestToDto(deviceRequest);

        return deviceRepository.findById(id).map(device -> {
            device.setName(deviceDto.getName());
            device.setBrand(deviceDto.getBrand());
            return deviceRepository.save(device);
        }).orElseThrow(() -> new DeviceNotFoundException(id));
    }
}
