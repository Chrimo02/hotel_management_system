package mapperTestsPersistenceDomain;

import static org.junit.jupiter.api.Assertions.*;

import hotelmanagementsystem.domain.models.RoomIdentifier;
import hotelmanagementsystem.infrastructure.persistence.entities.RoomIdentifierEntity;
import hotelmanagementsystem.infrastructure.persistence.mapper.RoomIdentifierMapper;
import org.junit.jupiter.api.Test;

public class RoomIdentifierMapperTest {

    private RoomIdentifierMapper mapper = new RoomIdentifierMapper();

    @Test
    public void testDomainToEntity() {
        RoomIdentifier domain = new RoomIdentifier("BuildingA", 1, "101A");
        RoomIdentifierEntity entity = mapper.domainToEntity(domain);
        assertNotNull(entity);
        assertEquals(domain.getBuilding(), entity.getBuilding());
        assertEquals(domain.getFloor(), entity.getFloor());
        assertEquals(domain.getRoomNumber(), entity.getRoomNumber());
    }

    @Test
    public void testEntityToDomain() {
        RoomIdentifierEntity entity = new RoomIdentifierEntity("BuildingB", 2, "202B");
        RoomIdentifier domain = mapper.entityToDomain(entity);
        assertNotNull(domain);
        assertEquals(entity.getBuilding(), domain.getBuilding());
        assertEquals(entity.getFloor(), domain.getFloor());
        assertEquals(entity.getRoomNumber(), domain.getRoomNumber());
    }
}
