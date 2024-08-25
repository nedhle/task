package app.controller;

import app.controller.request.DeviceRequest;
import app.model.Device;
import app.service.DeviceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/devices")
@RequiredArgsConstructor
@Tag(name = "Device Controller", description = "API for managing devices")
public class DeviceController {

    private final DeviceService deviceService;

    @Operation(summary = "Add a new device")
    @PostMapping
    public ResponseEntity<Device> addDevice(@RequestBody DeviceRequest deviceRequest) {
        return ResponseEntity.ok(deviceService.addDevice(deviceRequest));
    }

    @Operation(summary = "Get a list of all devices")
    @GetMapping
    public ResponseEntity<List<Device>> listAllDevices() {
        return ResponseEntity.ok(deviceService.listAllDevices());
    }

    @Operation(summary = "Get a device by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Device> getDeviceById(@PathVariable Long id) {
        return deviceService.getDeviceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Update a device by ID")
    @PutMapping("/{id}")
    public ResponseEntity<Device> updateDevice(@PathVariable Long id, @RequestBody DeviceRequest deviceRequest) {
        return ResponseEntity.ok(deviceService.updateDevice(id, deviceRequest));
    }

    @Operation(summary = "Partially update a device by ID")
    @PatchMapping("/{id}")
    public ResponseEntity<Device> partialUpdateDevice(@PathVariable Long id, @RequestBody DeviceRequest deviceRequest) {
        return ResponseEntity.ok(deviceService.patchDevice(id, deviceRequest));
    }

    @Operation(summary = "Delete a device by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDevice(@PathVariable Long id) {
        deviceService.deleteDevice(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Search devices by brand")
    @GetMapping("/by-brand")
    public ResponseEntity<List<Device>> searchDevicesByBrand(@RequestParam String brand) {
        return ResponseEntity.ok(deviceService.searchDevicesByBrand(brand));
    }

}
