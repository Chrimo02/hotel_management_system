package DTOTests;

import static org.junit.jupiter.api.Assertions.*;

import hotelmanagementsystem.infrastructure.api.dto.HotelLocationDTO;
import hotelmanagementsystem.infrastructure.api.grpc.generated.HotelLocation;
import org.junit.jupiter.api.Test;

public class HotelLocationDTOTest {

    @Test
    public void testGettersAndSetters() {
        HotelLocationDTO dto = new HotelLocationDTO();
        dto.setAddress("123 Main St");
        dto.setCity("TestCity");
        dto.setCountry("TestCountry");

        assertEquals("123 Main St", dto.getAddress());
        assertEquals("TestCity", dto.getCity());
        assertEquals("TestCountry", dto.getCountry());
    }

    @Test
    public void testToProtobuf_AllFieldsSet() {
        HotelLocationDTO dto = new HotelLocationDTO();
        dto.setAddress("123 Main St");
        dto.setCity("TestCity");
        dto.setCountry("TestCountry");

        HotelLocation proto = dto.toProtobuf();
        assertEquals("123 Main St", proto.getAddress());
        assertEquals("TestCity", proto.getCity());
        assertEquals("TestCountry", proto.getCountry());
    }

    @Test
    public void testToProtobuf_NullFields() {
        HotelLocationDTO dto = new HotelLocationDTO();
        HotelLocation proto = dto.toProtobuf();
        assertEquals("", proto.getAddress());
        assertEquals("", proto.getCity());
        assertEquals("", proto.getCountry());
    }
}
