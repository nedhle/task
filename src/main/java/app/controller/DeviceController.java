package app.controller;

import app.controller.request.DeviceRequest;
import app.model.Device;
import app.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/devices")
@RequiredArgsConstructor
public class DeviceController {

    private final DeviceService deviceService;

    @PostMapping
    public ResponseEntity<Device> addDevice(@RequestBody DeviceRequest deviceRequest) {
        return ResponseEntity.ok(deviceService.addDevice(deviceRequest));
    }
}
