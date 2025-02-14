package entityTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import hotelmanagementsystem.infrastructure.persistence.entities.BookingEntity;
import hotelmanagementsystem.infrastructure.persistence.entities.RoomEntity;
import hotelmanagementsystem.infrastructure.persistence.entities.RoomIdentifierEntity;
import org.junit.jupiter.api.Test;

public class RoomEntityTest {

    @Test
    public void testGettersAndSetters() {
        RoomEntity room = new RoomEntity(100.0, new RoomIdentifierEntity("BuildingA", 1, "101A"), null) {
        };

        room.setId(10L);
        assertEquals(10L, room.getId());
        assertEquals(100.0, room.getPricePerNight(), 0.001);
        assertNotNull(room.getRoomIdentifier());

        Set<BookingEntity> bookings = new HashSet<>();
        room.setBookings(bookings);
        assertEquals(bookings, room.getBookings());
    }
}
