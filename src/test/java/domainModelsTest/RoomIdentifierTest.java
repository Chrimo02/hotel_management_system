package domainModelsTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.RoomIdentifier;
public class RoomIdentifierTest {
    @Test
    void testRoomIdentifierConstructorAndGetters() {
        RoomIdentifier roomIdentifier = new RoomIdentifier("BuildingA", 2, "102B");

        assertEquals("BuildingA", roomIdentifier.getBuilding());
        assertEquals(2, roomIdentifier.getFloor());
        assertEquals("102B", roomIdentifier.getRoomNumber());
    }

    @Test
    void testRoomIdentifierWithIdConstructor() {
        RoomIdentifier roomIdentifier = new RoomIdentifier(1L, "BuildingB", 3, "103C");

        assertEquals(1L, roomIdentifier.getId());
        assertEquals("BuildingB", roomIdentifier.getBuilding());
        assertEquals(3, roomIdentifier.getFloor());
        assertEquals("103C", roomIdentifier.getRoomNumber());
    }

    @Test
    void testSetBuilding() {
        RoomIdentifier roomIdentifier = new RoomIdentifier("BuildingA", 1, "101A");
        roomIdentifier.setBuilding("BuildingX");

        assertEquals("BuildingX", roomIdentifier.getBuilding());
    }

    @Test
    void testSetFloor() {
        RoomIdentifier roomIdentifier = new RoomIdentifier("BuildingA", 1, "101A");
        roomIdentifier.setFloor(5);

        assertEquals(5, roomIdentifier.getFloor());
    }

    @Test
    void testSetRoomNumber() {
        RoomIdentifier roomIdentifier = new RoomIdentifier("BuildingA", 1, "101A");
        roomIdentifier.setRoomNumber("105X");

        assertEquals("105X", roomIdentifier.getRoomNumber());
    }
}
