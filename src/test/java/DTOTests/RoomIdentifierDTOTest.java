package DTOTests;

import static org.junit.jupiter.api.Assertions.*;

import hotelmanagementsystem.infrastructure.api.dto.RoomIdentifierDTO;
import hotelmanagementsystem.infrastructure.api.grpc.generated.RoomIdentifier;
import org.junit.jupiter.api.Test;

public class RoomIdentifierDTOTest {

    @Test
    public void testConstructorAndGetters() {
        RoomIdentifierDTO dto = new RoomIdentifierDTO("BuildingA", 1, "101A");
        assertEquals("BuildingA", dto.getBuilding());
        assertEquals(1, dto.getFloor());
        assertEquals("101A", dto.getRoomNumber());
    }

    @Test
    public void testSetters() {
        RoomIdentifierDTO dto = new RoomIdentifierDTO("BuildingA", 1, "101A");
        dto.setBuilding("BuildingX");
        dto.setFloor(3);
        dto.setRoomNumber("303B");

        assertEquals("BuildingX", dto.getBuilding());
        assertEquals(3, dto.getFloor());
        assertEquals("303B", dto.getRoomNumber());
    }

    @Test
    public void testToProtobuf() {
        RoomIdentifierDTO dto = new RoomIdentifierDTO("BuildingC", 4, "404C");
        RoomIdentifier proto = dto.toProtobuf();
        assertEquals("BuildingC", proto.getBuilding());
        assertEquals(4, proto.getFloor());
        assertEquals("404C", proto.getRoomNumber());
    }
}
