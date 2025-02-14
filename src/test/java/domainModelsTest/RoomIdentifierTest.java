package domainModelsTest;

import static org.junit.jupiter.api.Assertions.*;

import hotelmanagementsystem.domain.models.RoomIdentifier;
import org.junit.jupiter.api.Test;

public class RoomIdentifierTest {

    @Test
    public void testConstructorAndGetters() {
        String building = "BuildingA";
        int floor = 3;
        String roomNumber = "305B";

        RoomIdentifier identifier = new RoomIdentifier(building, floor, roomNumber);

        assertEquals(building, identifier.getBuilding(), "Building should match");
        assertEquals(floor, identifier.getFloor(), "Floor should match");
        assertEquals(roomNumber, identifier.getRoomNumber(), "Room number should match");
        assertEquals(0L, identifier.getId(), "Default id should be 0");
    }

    @Test
    public void testSetters() {
        RoomIdentifier identifier = new RoomIdentifier("OldBuilding", 1, "101A");

        identifier.setBuilding("NewBuilding");
        identifier.setFloor(5);
        identifier.setRoomNumber("505C");

        assertEquals("NewBuilding", identifier.getBuilding(), "Building should be updated");
        assertEquals(5, identifier.getFloor(), "Floor should be updated");
        assertEquals("505C", identifier.getRoomNumber(), "Room number should be updated");
    }
}
