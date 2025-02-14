package entityTests;

import static org.junit.jupiter.api.Assertions.*;

import hotelmanagementsystem.infrastructure.persistence.entities.HotelEntity;
import hotelmanagementsystem.infrastructure.persistence.entities.RoomIdentifierEntity;
import hotelmanagementsystem.infrastructure.persistence.entities.SingleRoomEntity;
import org.junit.jupiter.api.Test;

public class SingleRoomEntityTest {

    @Test
    public void testNoArgConstructor() {
        SingleRoomEntity entity = new SingleRoomEntity();
        assertNotNull(entity);
    }

    @Test
    public void testConstructorWithId() {
        HotelEntity dummyHotel = new HotelEntity.HotelBuilder()
                .withId(1L)
                .withName("Test Hotel")
                .build();
        RoomIdentifierEntity dummyIdentifier = new RoomIdentifierEntity("BuildingA", 1, "101A");
        SingleRoomEntity entity = new SingleRoomEntity(10L, 150.0, dummyIdentifier, dummyHotel);
        assertEquals(150.0, entity.getPricePerNight(), 0.001);
        assertEquals(dummyIdentifier, entity.getRoomIdentifier());
        assertEquals(dummyHotel, entity.getHotel());
    }

    @Test
    public void testConstructorWithoutId() {
        HotelEntity dummyHotel = new HotelEntity.HotelBuilder()
                .withId(1L)
                .withName("Test Hotel")
                .build();
        RoomIdentifierEntity dummyIdentifier = new RoomIdentifierEntity("BuildingA", 1, "101A");
        SingleRoomEntity entity = new SingleRoomEntity(150.0, dummyIdentifier, dummyHotel);
        assertEquals(150.0, entity.getPricePerNight(), 0.001);
        assertEquals(dummyIdentifier, entity.getRoomIdentifier());
        assertEquals(dummyHotel, entity.getHotel());
    }
}
