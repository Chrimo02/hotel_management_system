package mapperTestsDomainDTO;

import static org.junit.jupiter.api.Assertions.*;

import hotelmanagementsystem.domain.models.RoomIdentifier;
import hotelmanagementsystem.infrastructure.api.dto.RoomIdentifierDTO;
import hotelmanagementsystem.infrastructure.api.mapper.RoomIdentifierMapper;
import org.junit.jupiter.api.Test;

public class RoomIdentifierMapperTest {

    @Test
    public void testToDTO() {
        RoomIdentifier roomIdentifier = new RoomIdentifier("BuildingA", 1, "101A");
        RoomIdentifierDTO dto = RoomIdentifierMapper.toDTO(roomIdentifier);
        assertNotNull(dto);
        assertEquals("BuildingA", dto.getBuilding());
        assertEquals(1, dto.getFloor());
        assertEquals("101A", dto.getRoomNumber());
    }

    @Test
    public void testToDomain() {
        RoomIdentifierDTO dto = new RoomIdentifierDTO("BuildingB", 2, "202B");
        RoomIdentifier roomIdentifier = RoomIdentifierMapper.toDomain(dto);
        assertNotNull(roomIdentifier);
        assertEquals("BuildingB", roomIdentifier.getBuilding());
        assertEquals(2, roomIdentifier.getFloor());
        assertEquals("202B", roomIdentifier.getRoomNumber());
    }
}
