package entityTests;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import hotelmanagementsystem.infrastructure.persistence.entities.*;
import org.junit.jupiter.api.Test;

public class BookingEntityTest {

    @Test
    public void testFullConstructorAndGetters() {
        // Dummy-Objekte für Abhängigkeiten
        HotelEntity dummyHotel = new HotelEntity.HotelBuilder()
                .withId(1L)
                .withName("Dummy Hotel")
                .build();
        RoomEntity dummyRoom = new SingleRoomEntity(100.0, new RoomIdentifierEntity("BuildingA", 1, "101A"), dummyHotel);
        GuestEntity dummyGuest = new GuestEntity("John", "Doe", 1990, 1, 1, "john@example.com", "123456789");

        LocalDate checkIn = LocalDate.of(2025, 1, 1);
        LocalDate checkOut = LocalDate.of(2025, 1, 5);
        LocalDateTime checkInTime = LocalDateTime.of(2025, 1, 1, 14, 0);
        LocalDateTime checkOutTime = LocalDateTime.of(2025, 1, 5, 11, 0);
        List<RoomEntity> rooms = Arrays.asList(dummyRoom);
        List<GuestEntity> guests = Arrays.asList(dummyGuest);

        BookingEntity booking = new BookingEntity(10L, dummyHotel, checkIn, checkOut, rooms, guests, true, checkInTime, checkOutTime);

        assertEquals(10L, booking.getId());
        assertEquals(dummyHotel, booking.getHotel());
        assertEquals(checkIn, booking.getCheckInDate());
        assertEquals(checkOut, booking.getCheckOutDate());
        assertEquals(rooms, booking.getRooms());
        assertEquals(guests, booking.getGuests());
        assertTrue(booking.isStatus());
        assertEquals(checkInTime, booking.getCheckInTime());
        assertEquals(checkOutTime, booking.getCheckOutTime());
    }

    @Test
    public void testSimpleConstructorDefaults() {
        HotelEntity dummyHotel = new HotelEntity.HotelBuilder()
                .withId(1L)
                .withName("Dummy Hotel")
                .build();
        List<RoomEntity> rooms = Arrays.asList();
        List<GuestEntity> guests = Arrays.asList();
        LocalDate checkIn = LocalDate.of(2025, 2, 1);
        LocalDate checkOut = LocalDate.of(2025, 2, 3);

        BookingEntity booking = new BookingEntity(dummyHotel, checkIn, checkOut, rooms, guests);

        // Standardmäßig wird status true gesetzt, checkInTime und checkOutTime bleiben null.
        assertTrue(booking.isStatus());
        assertNull(booking.getCheckInTime());
        assertNull(booking.getCheckOutTime());
    }

    @Test
    public void testSetters() {
        HotelEntity dummyHotel = new HotelEntity.HotelBuilder()
                .withId(1L)
                .withName("Dummy Hotel")
                .build();
        List<RoomEntity> rooms = Arrays.asList();
        List<GuestEntity> guests = Arrays.asList();
        LocalDate checkIn = LocalDate.of(2025, 3, 1);
        LocalDate checkOut = LocalDate.of(2025, 3, 2);

        BookingEntity booking = new BookingEntity(dummyHotel, checkIn, checkOut, rooms, guests);

        // Setze neue Werte
        booking.setStatus(false);
        LocalDateTime now = LocalDateTime.now();
        booking.setCheckInTime(now);
        booking.setCheckOutTime(now.plusHours(5));
        booking.setRooms(Arrays.asList(new SingleRoomEntity(150.0, new RoomIdentifierEntity("BuildingB", 2, "202B"), dummyHotel)));

        assertFalse(booking.isStatus());
        assertEquals(now, booking.getCheckInTime());
        assertEquals(now.plusHours(5), booking.getCheckOutTime());
        assertEquals(1, booking.getRooms().size());
    }
}
