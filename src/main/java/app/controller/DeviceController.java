package app.controller;

import app.controller.request.DeviceRequest;
import app.model.Device;
import app.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/devices")
@RequiredArgsConstructor
public class DeviceController {

    private final DeviceService deviceService;

    @PostMapping
    public ResponseEntity<Device> addDevice(@RequestBody DeviceRequest deviceRequest) {
        return ResponseEntity.ok(deviceService.addDevice(deviceRequest));
    }

    @GetMapping
    public ResponseEntity<List<Device>> listAllDevices() {
        return ResponseEntity.ok(deviceService.listAllDevices());
    }
}
