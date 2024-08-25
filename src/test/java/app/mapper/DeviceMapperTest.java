package app.mapper;

import app.controller.request.DeviceRequest;
import app.dto.DeviceDto;
import app.model.Device;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DeviceMapperTest {

    private final DeviceMapper deviceMapper = DeviceMapper.INSTANCE;

    @Test
    void testDtoToEntity() {
        DeviceDto deviceDto = DeviceDto.builder()
                .name("Test Device")
                .brand("Test Brand")
                .build();

        Device device = deviceMapper.dtoToEntity(deviceDto);

        assertNotNull(device);
        assertEquals(deviceDto.getName(), device.getName());
        assertEquals(deviceDto.getBrand(), device.getBrand());
    }

    @Test
    void testRequestToDto() {
        DeviceRequest deviceRequest = new DeviceRequest("Test Device", "Test Brand");

        DeviceDto deviceDto = deviceMapper.requestToDto(deviceRequest);

        assertNotNull(deviceDto);
        assertEquals(deviceRequest.getName(), deviceDto.getName());
        assertEquals(deviceRequest.getBrand(), deviceDto.getBrand());
    }

    @Test
    void testEntityToDtoRoundTrip() {
        DeviceDto deviceDto = DeviceDto.builder()
                .name("Test Device")
                .brand("Test Brand")
                .build();

        Device device = deviceMapper.dtoToEntity(deviceDto);
        DeviceDto mappedBackDto = deviceMapper.requestToDto(new DeviceRequest(device.getName(), device.getBrand()));

        assertNotNull(mappedBackDto);
        assertEquals(deviceDto.getName(), mappedBackDto.getName());
        assertEquals(deviceDto.getBrand(), mappedBackDto.getBrand());
    }
}
