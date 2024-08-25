package app.controller;

import app.controller.request.DeviceRequest;
import app.model.Device;
import app.service.DeviceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class DeviceControllerTest {

    @Mock
    private DeviceService deviceService;

    @InjectMocks
    private DeviceController deviceController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddDevice() {
        DeviceRequest deviceRequest = new DeviceRequest("Device1", "Brand1");
        Device device = new Device(1L, "Device1", "Brand1", null);

        when(deviceService.addDevice(any(DeviceRequest.class))).thenReturn(device);

        ResponseEntity<Device> response = deviceController.addDevice(deviceRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(device, response.getBody());
        verify(deviceService).addDevice(deviceRequest);
    }

    @Test
    void testListAllDevices() {
        Device device = new Device(1L, "Device1", "Brand1", null);

        when(deviceService.listAllDevices()).thenReturn(Collections.singletonList(device));

        ResponseEntity<List<Device>> response = deviceController.listAllDevices();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(device, response.getBody().get(0));
        verify(deviceService).listAllDevices();
    }

    @Test
    void testGetDeviceById_Success() {
        Device device = new Device(1L, "Device1", "Brand1", null);

        when(deviceService.getDeviceById(1L)).thenReturn(Optional.of(device));

        ResponseEntity<Device> response = deviceController.getDeviceById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(device, response.getBody());
        verify(deviceService).getDeviceById(1L);
    }

    @Test
    void testGetDeviceById_NotFound() {
        when(deviceService.getDeviceById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Device> response = deviceController.getDeviceById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(deviceService).getDeviceById(1L);
    }

    @Test
    void testUpdateDevice() {
        DeviceRequest deviceRequest = new DeviceRequest("UpdatedDevice", "UpdatedBrand");
        Device updatedDevice = new Device(1L, "UpdatedDevice", "UpdatedBrand", null);

        when(deviceService.updateDevice(1L, deviceRequest)).thenReturn(updatedDevice);

        ResponseEntity<Device> response = deviceController.updateDevice(1L, deviceRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedDevice, response.getBody());
        verify(deviceService).updateDevice(1L, deviceRequest);
    }

    @Test
    void testPartialUpdateDevice() {
        DeviceRequest deviceRequest = new DeviceRequest("PatchedDevice", "PatchedBrand");
        Device patchedDevice = new Device(1L, "PatchedDevice", "PatchedBrand", null);

        when(deviceService.patchDevice(1L, deviceRequest)).thenReturn(patchedDevice);

        ResponseEntity<Device> response = deviceController.partialUpdateDevice(1L, deviceRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(patchedDevice, response.getBody());
        verify(deviceService).patchDevice(1L, deviceRequest);
    }

    @Test
    void testDeleteDevice() {
        doNothing().when(deviceService).deleteDevice(1L);

        ResponseEntity<Void> response = deviceController.deleteDevice(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(deviceService).deleteDevice(1L);
    }

    @Test
    void testSearchDevicesByBrand() {
        Device device = new Device(1L, "Device1", "Brand1", null);

        when(deviceService.searchDevicesByBrand("Brand1")).thenReturn(Collections.singletonList(device));

        ResponseEntity<List<Device>> response = deviceController.searchDevicesByBrand("Brand1");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(device, response.getBody().get(0));
        verify(deviceService).searchDevicesByBrand("Brand1");
    }
}
