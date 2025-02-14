package entityTests;

import static org.junit.jupiter.api.Assertions.*;

import hotelmanagementsystem.infrastructure.persistence.entities.DoubleRoomEntity;
import hotelmanagementsystem.infrastructure.persistence.entities.HotelEntity;
import hotelmanagementsystem.infrastructure.persistence.entities.RoomIdentifierEntity;
import org.junit.jupiter.api.Test;

public class DoubleRoomEntityTest {

    @Test
    public void testNoArgConstructor() {
        DoubleRoomEntity entity = new DoubleRoomEntity();
        assertNotNull(entity);
    }

    @Test
    public void testConstructorWithId() {
        HotelEntity dummyHotel = new HotelEntity.HotelBuilder()
                .withId(1L)
                .withName("Dummy Hotel")
                .build();
        RoomIdentifierEntity dummyIdentifier = new RoomIdentifierEntity("BuildingA", 1, "101A");
        DoubleRoomEntity entity = new DoubleRoomEntity(10L, 200.0, dummyIdentifier, dummyHotel);
        assertEquals(200.0, entity.getPricePerNight(), 0.001);
        assertEquals(dummyIdentifier, entity.getRoomIdentifier());
        assertEquals(dummyHotel, entity.getHotel());
    }

    @Test
    public void testConstructorWithoutId() {
        HotelEntity dummyHotel = new HotelEntity.HotelBuilder()
                .withId(1L)
                .withName("Dummy Hotel")
                .build();
        RoomIdentifierEntity dummyIdentifier = new RoomIdentifierEntity("BuildingA", 1, "101A");
        DoubleRoomEntity entity = new DoubleRoomEntity(250.0, dummyIdentifier, dummyHotel);
        assertEquals(250.0, entity.getPricePerNight(), 0.001);
        assertEquals(dummyIdentifier, entity.getRoomIdentifier());
        assertEquals(dummyHotel, entity.getHotel());
    }
}
