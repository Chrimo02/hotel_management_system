package domainModelsTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import hotelmanagementsystem.domain.models.Booking;
import hotelmanagementsystem.domain.models.Hotel;
import hotelmanagementsystem.domain.models.Room;
import hotelmanagementsystem.domain.models.RoomIdentifier;
import org.junit.jupiter.api.Test;

public class RoomTest {

    @Test
    public void testBuilderCreatesRoomWithExpectedValues() {
        long expectedId = 10L;
        double expectedPrice = 120.0;
        RoomIdentifier expectedIdentifier = new RoomIdentifier("Building1", 3, "305");

        Hotel dummyHotel = new Hotel.HotelBuilder()
                .withId(1L)
                .withName("Dummy Hotel")
                .withDescription("Dummy Description")
                .build();

        Room room = new Room.Builder()
                .withId(expectedId)
                .withPricePerNight(expectedPrice)
                .withRoomIdentifier(expectedIdentifier)
                .withHotel(dummyHotel)
                .build();

        assertNotNull(room, "Der Room sollte nicht null sein");

        assertEquals(expectedPrice, room.getPricePerNight(), 0.001, "Price per night muss stimmen");

        assertEquals(expectedIdentifier, room.getRoomIdentifier(), "RoomIdentifier muss stimmen");

        assertEquals(dummyHotel, room.getHotel(), "Hotel muss stimmen");

        Set<Booking> bookings = room.getBookings();
        assertNotNull(bookings, "Die Buchungsmenge darf nicht null sein");
        assertTrue(bookings.isEmpty(), "Die Buchungsmenge sollte initial leer sein");
    }
}
