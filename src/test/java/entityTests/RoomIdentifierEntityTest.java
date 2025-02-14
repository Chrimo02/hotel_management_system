package entityTests;

import static org.junit.jupiter.api.Assertions.*;

import hotelmanagementsystem.infrastructure.persistence.entities.RoomIdentifierEntity;
import org.junit.jupiter.api.Test;

public class RoomIdentifierEntityTest {

    @Test
    public void testConstructorAndGetters() {
        RoomIdentifierEntity entity = new RoomIdentifierEntity("BuildingA", 1, "101A");
        assertEquals("BuildingA", entity.getBuilding());
        assertEquals(1, entity.getFloor());
        assertEquals("101A", entity.getRoomNumber());
    }

    @Test
    public void testSetters() {
        RoomIdentifierEntity entity = new RoomIdentifierEntity("BuildingA", 1, "101A");
        entity.setBuilding("BuildingB");
        entity.setFloor(2);
        entity.setRoomNumber("202B");

        assertEquals("BuildingB", entity.getBuilding());
        assertEquals(2, entity.getFloor());
        assertEquals("202B", entity.getRoomNumber());
    }
}
