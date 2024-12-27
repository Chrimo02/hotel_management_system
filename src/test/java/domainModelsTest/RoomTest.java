package domainModelsTest;
import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
public class RoomTest {
    private Room singleRoom;
    private Room doubleRoom;
    private RoomIdentifier testRoomIdentifier;
    private Hotel testHotel;
    private Booking testBooking;

    @BeforeEach
    void setUp() {
        testRoomIdentifier = new RoomIdentifier("BuildingA", 1, "101A");
        testHotel = new Hotel.HotelBuilder()
                .withName("Test Hotel")
                .withDescription("A test hotel")
                .build();
        singleRoom = new SingleRoom.Builder(150.0, testRoomIdentifier, testHotel)
                .withId(1L)
                .build();
        doubleRoom = new DoubleRoom.Builder(200.0, testRoomIdentifier, testHotel)
                .withId(2L)
                .build();

        testBooking = new Booking(
                1L,
                testHotel,
                LocalDate.of(2024, 1, 1),
                LocalDate.of(2024, 1, 5),
                List.of(singleRoom, doubleRoom),
                List.of(),
                true,
                null,
                null
        );
    }

    @Test
    void testGetAndSetPricePerNight() {
        assertEquals(150.0, singleRoom.getPricePerNight());
        singleRoom.setPricePerNight(175.0);
        assertEquals(175.0, singleRoom.getPricePerNight());

        assertEquals(200.0, doubleRoom.getPricePerNight());
        doubleRoom.setPricePerNight(225.0);
        assertEquals(225.0, doubleRoom.getPricePerNight());
    }

    @Test
    void testGetAndSetRoomIdentifier() {
        RoomIdentifier newIdentifier = new RoomIdentifier("BuildingB", 2, "202B");
        assertEquals("BuildingA", singleRoom.getRoomIdentifier().getBuilding());
        singleRoom.setRoomIdentifier(newIdentifier);
        assertEquals("BuildingB", singleRoom.getRoomIdentifier().getBuilding());

        assertEquals("BuildingA", doubleRoom.getRoomIdentifier().getBuilding());
        doubleRoom.setRoomIdentifier(newIdentifier);
        assertEquals("BuildingB", doubleRoom.getRoomIdentifier().getBuilding());
    }

    @Test
    void testGetAndSetHotel() {
        Hotel newHotel = new Hotel.HotelBuilder().withName("New Test Hotel").build();
        assertEquals("Test Hotel", singleRoom.getHotel().getName());
        singleRoom.setHotel(newHotel);
        assertEquals("New Test Hotel", singleRoom.getHotel().getName());

        assertEquals("Test Hotel", doubleRoom.getHotel().getName());
        doubleRoom.setHotel(newHotel);
        assertEquals("New Test Hotel", doubleRoom.getHotel().getName());
    }

    @Test
    void testGetAndSetBookings() {
        Booking booking1 = new Booking(2L, testHotel, LocalDate.of(2024, 2, 1), LocalDate.of(2024, 2, 5), List.of(singleRoom), List.of(), true, null, null);
        Booking booking2 = new Booking(3L, testHotel, LocalDate.of(2024, 3, 1), LocalDate.of(2024, 3, 5), List.of(doubleRoom), List.of(), true, null, null);

        singleRoom.setBookings(Set.of(booking1));
        doubleRoom.setBookings(Set.of(booking2));

        assertEquals(1, singleRoom.getBookings().size());
        assertTrue(singleRoom.getBookings().contains(booking1));

        assertEquals(1, doubleRoom.getBookings().size());
        assertTrue(doubleRoom.getBookings().contains(booking2));
    }

    @Test
    void testRoomId() {
        assertEquals(1L, singleRoom.getId());
        assertEquals(2L, doubleRoom.getId());
    }

    @Test
    void testCalculateTotalPrice() {
        double expectedTotalPrice = 150.0 * Booking.calculateNights(testBooking.getCheckInDate(), testBooking.getCheckOutDate())
                + 200.0 * Booking.calculateNights(testBooking.getCheckInDate(), testBooking.getCheckOutDate());
        assertEquals(expectedTotalPrice, testBooking.getTotalPrice());
    }
}
