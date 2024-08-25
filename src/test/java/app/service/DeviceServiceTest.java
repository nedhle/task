package app.service;

import app.controller.request.DeviceRequest;
import app.dto.DeviceDto;
import app.exception.DeviceNotFoundException;
import app.mapper.DeviceMapper;
import app.model.Device;
import app.repository.DeviceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DeviceServiceTest {

    @Mock
    private DeviceRepository deviceRepository;

    @Mock
    private DeviceMapper deviceMapper;

    @InjectMocks
    private DeviceService deviceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddDevice() {
        DeviceRequest deviceRequest = new DeviceRequest("Device1", "Brand1");
        DeviceDto deviceDto = new DeviceDto("Device1", "Brand1");
        Device device = new Device(null, "Device1", "Brand1", null);

        when(deviceMapper.requestToDto(deviceRequest)).thenReturn(deviceDto);
        when(deviceMapper.dtoToEntity(deviceDto)).thenReturn(device);
        when(deviceRepository.save(device)).thenReturn(device);

        Device savedDevice = deviceService.addDevice(deviceRequest);

        assertEquals(device, savedDevice);
        verify(deviceRepository).save(device);
    }

    @Test
    void testListAllDevices() {
        Device device = new Device(1L, "Device1", "Brand1", null);

        when(deviceRepository.findAll()).thenReturn(Collections.singletonList(device));

        List<Device> devices = deviceService.listAllDevices();

        assertEquals(1, devices.size());
        assertEquals(device, devices.get(0));
        verify(deviceRepository).findAll();
    }

    @Test
    void testGetDeviceById_Success() {
        Device device = new Device(1L, "Device1", "Brand1", null);

        when(deviceRepository.findById(1L)).thenReturn(Optional.of(device));

        Optional<Device> foundDevice = deviceService.getDeviceById(1L);

        assertTrue(foundDevice.isPresent());
        assertEquals(device, foundDevice.get());
        verify(deviceRepository).findById(1L);
    }

    @Test
    void testGetDeviceById_NotFound() {
        when(deviceRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(DeviceNotFoundException.class, () -> deviceService.getDeviceById(1L));
        verify(deviceRepository).findById(1L);
    }

    @Test
    void testUpdateDevice_Success() {
        DeviceRequest deviceRequest = new DeviceRequest("UpdatedDevice", "UpdatedBrand");
        DeviceDto deviceDto = new DeviceDto("UpdatedDevice", "UpdatedBrand");
        Device existingDevice = new Device(1L, "Device1", "Brand1", null);
        Device updatedDevice = new Device(1L, "UpdatedDevice", "UpdatedBrand", null);

        when(deviceMapper.requestToDto(deviceRequest)).thenReturn(deviceDto);
        when(deviceRepository.findById(1L)).thenReturn(Optional.of(existingDevice));
        when(deviceRepository.save(existingDevice)).thenReturn(updatedDevice);

        Device result = deviceService.updateDevice(1L, deviceRequest);

        assertEquals("UpdatedDevice", result.getName());
        assertEquals("UpdatedBrand", result.getBrand());
        verify(deviceRepository).findById(1L);
        verify(deviceRepository).save(existingDevice);
    }

    @Test
    void testUpdateDevice_NotFound() {
        DeviceRequest deviceRequest = new DeviceRequest("UpdatedDevice", "UpdatedBrand");

        when(deviceRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(DeviceNotFoundException.class, () -> deviceService.updateDevice(1L, deviceRequest));
        verify(deviceRepository).findById(1L);
    }

    @Test
    void testPatchDevice_Success() {
        DeviceRequest deviceRequest = new DeviceRequest("PatchedDevice", "PatchedBrand");
        DeviceDto deviceDto = new DeviceDto("PatchedDevice", "PatchedBrand");
        Device existingDevice = new Device(1L, "Device1", "Brand1", null);

        when(deviceMapper.requestToDto(deviceRequest)).thenReturn(deviceDto);
        when(deviceRepository.findById(1L)).thenReturn(Optional.of(existingDevice));
        when(deviceRepository.save(existingDevice)).thenReturn(existingDevice);

        Device result = deviceService.patchDevice(1L, deviceRequest);

        assertEquals("PatchedDevice", result.getName());
        assertEquals("PatchedBrand", result.getBrand());
        verify(deviceRepository).findById(1L);
        verify(deviceRepository).save(existingDevice);
    }

    @Test
    void testPatchDevice_NotFound() {
        DeviceRequest deviceRequest = new DeviceRequest("PatchedDevice", "PatchedBrand");

        when(deviceRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(DeviceNotFoundException.class, () -> deviceService.patchDevice(1L, deviceRequest));
        verify(deviceRepository).findById(1L);
    }

    @Test
    void testDeleteDevice_Success() {
        Device device = new Device(1L, "Device1", "Brand1", null);

        when(deviceRepository.findById(1L)).thenReturn(Optional.of(device));

        deviceService.deleteDevice(1L);

        verify(deviceRepository).findById(1L);
        verify(deviceRepository).deleteById(1L);
    }

    @Test
    void testDeleteDevice_NotFound() {
        when(deviceRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(DeviceNotFoundException.class, () -> deviceService.deleteDevice(1L));
        verify(deviceRepository).findById(1L);
    }

    @Test
    void testSearchDevicesByBrand() {
        Device device = new Device(1L, "Device1", "Brand1", null);

        when(deviceRepository.findByBrand("Brand1")).thenReturn(Collections.singletonList(device));

        List<Device> devices = deviceService.searchDevicesByBrand("Brand1");

        assertEquals(1, devices.size());
        assertEquals(device, devices.get(0));
        verify(deviceRepository).findByBrand("Brand1");
    }
}
