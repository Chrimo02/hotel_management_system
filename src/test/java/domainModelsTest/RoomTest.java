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

        // Erstelle ein Dummy-Hotel (minimaler Aufbau)
        Hotel dummyHotel = new Hotel.HotelBuilder()
                .withId(1L)
                .withName("Dummy Hotel")
                .withDescription("Dummy Description")
                .build();

        // Erzeuge einen Room über den Builder (anonymer Subtyp von Room)
        Room room = new Room.Builder()
                .withId(expectedId)
                .withPricePerNight(expectedPrice)
                .withRoomIdentifier(expectedIdentifier)
                .withHotel(dummyHotel)
                .build();

        // Überprüfe, ob das Room-Objekt nicht null ist
        assertNotNull(room, "Der Room sollte nicht null sein");

        // Überprüfe, ob der Preis korrekt gesetzt wurde
        assertEquals(expectedPrice, room.getPricePerNight(), 0.001, "Price per night muss stimmen");

        // Überprüfe, ob der RoomIdentifier übereinstimmt
        assertEquals(expectedIdentifier, room.getRoomIdentifier(), "RoomIdentifier muss stimmen");

        // Überprüfe, ob das Hotel korrekt gesetzt wurde
        assertEquals(dummyHotel, room.getHotel(), "Hotel muss stimmen");

        // Überprüfe, dass die Buchungen initial leer sind
        Set<Booking> bookings = room.getBookings();
        assertNotNull(bookings, "Die Buchungsmenge darf nicht null sein");
        assertTrue(bookings.isEmpty(), "Die Buchungsmenge sollte initial leer sein");
    }
}
