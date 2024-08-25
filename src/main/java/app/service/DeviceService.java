package app.service;

import app.controller.request.DeviceRequest;
import app.dto.DeviceDto;
import app.exception.DeviceNotFoundException;
import app.mapper.DeviceMapper;
import app.model.Device;
import app.repository.DeviceRepository;
import app.util.PatchUtil;
import java.util.List;
import java.util.Optional;
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

  public List<Device> listAllDevices() {
    return deviceRepository.findAll();
  }

  public Optional<Device> getDeviceById(Long id) {
    return Optional.ofNullable(
        deviceRepository.findById(id).orElseThrow(() -> new DeviceNotFoundException(id)));
  }

  public Device updateDevice(Long id, DeviceRequest deviceRequest) {
    DeviceDto deviceDto = deviceMapper.requestToDto(deviceRequest);

    return deviceRepository
        .findById(id)
        .map(
            device -> {
              device.setName(deviceDto.getName());
              device.setBrand(deviceDto.getBrand());
              return deviceRepository.save(device);
            })
        .orElseThrow(() -> new DeviceNotFoundException(id));
  }

  public Device patchDevice(Long id, DeviceRequest deviceRequest) {
    DeviceDto deviceDto = deviceMapper.requestToDto(deviceRequest);

    return deviceRepository
        .findById(id)
        .map(
            device -> {
              PatchUtil.updateNonNullFields(deviceDto, device);
              return deviceRepository.save(device);
            })
        .orElseThrow(() -> new DeviceNotFoundException(id));
  }

  public void deleteDevice(Long id) {
    deviceRepository.findById(id).orElseThrow(() -> new DeviceNotFoundException(id));
    deviceRepository.deleteById(id);
  }

  public List<Device> searchDevicesByBrand(String brand) {
    return deviceRepository.findByBrand(brand);
  }
}
