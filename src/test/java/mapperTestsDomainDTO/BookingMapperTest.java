package mapperTestsDomainDTO;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import hotelmanagementsystem.domain.models.Booking;
import hotelmanagementsystem.domain.models.Guest;
import hotelmanagementsystem.domain.models.Hotel;
import hotelmanagementsystem.domain.models.Room;
import hotelmanagementsystem.domain.models.SingleRoom;
import hotelmanagementsystem.domain.models.RoomIdentifier;
import hotelmanagementsystem.infrastructure.api.dto.BookingDTO;
import hotelmanagementsystem.infrastructure.api.mapper.BookingMapper;
import org.junit.jupiter.api.Test;

public class BookingMapperTest {

    @Test
    public void testToDTO() {
        // Erzeuge Dummy-Domain-Objekte
        Hotel dummyHotel = new Hotel.HotelBuilder()
                .withId(1L)
                .withName("Test Hotel")
                .build();
        RoomIdentifier roomId = new RoomIdentifier("BuildingA", 1, "101A");
        Room dummyRoom = new SingleRoom.Builder(100.0, roomId, dummyHotel)
                .withId(10L)
                .build();
        Guest dummyGuest = new Guest.GuestBuilder()
                .withId(20L)
                .withFirstName("Alice")
                .withLastName("Smith")
                .withBirthday(1990, 1, 1)
                .withEMail("alice@example.com")
                .withPhoneNumber("123456789")
                .build();
        LocalDate checkIn = LocalDate.of(2025, 12, 1);
        LocalDate checkOut = LocalDate.of(2025, 12, 5);
        LocalDateTime checkInTime = LocalDateTime.of(2025, 12, 1, 15, 0);
        LocalDateTime checkOutTime = LocalDateTime.of(2025, 12, 5, 11, 0);

        Booking booking = new Booking(
                100L,
                dummyHotel,
                checkIn,
                checkOut,
                Collections.singletonList(dummyRoom),
                Collections.singletonList(dummyGuest),
                true,
                checkInTime,
                checkOutTime
        );

        BookingDTO dto = BookingMapper.toDTO(booking);
        assertEquals(100L, dto.getId());
        assertEquals(dummyHotel.getId(), dto.getHotelId());
        assertEquals(Collections.singletonList(dummyGuest.getId()), dto.getGuestIds());
        assertEquals(Collections.singletonList(dummyRoom.getId()), dto.getRoomIds());
        assertEquals(checkIn, dto.getCheckInDate());
        assertEquals(checkOut, dto.getCheckOutDate());
        assertTrue(dto.isStatus());
        assertEquals(booking.getTotalPrice(), dto.getTotalPrice(), 0.001);
        assertEquals(checkInTime, dto.getCheckInTime());
        assertEquals(checkOutTime, dto.getCheckOutTime());
    }

    @Test
    public void testToDomain() {
        BookingDTO dto = new BookingDTO();
        dto.setId(200L);
        dto.setHotelId(2L);
        dto.setGuestIds(Arrays.asList(30L, 31L));
        dto.setRoomIds(Arrays.asList(40L, 41L));
        LocalDate checkIn = LocalDate.of(2026, 1, 10);
        LocalDate checkOut = LocalDate.of(2026, 1, 12); // 2 Nächte
        dto.setCheckInDate(checkIn);
        dto.setCheckOutDate(checkOut);
        dto.setStatus(true);
        dto.setTotalPrice(500.0);
        LocalDateTime checkInTime = LocalDateTime.of(2026, 1, 10, 14, 0);
        LocalDateTime checkOutTime = LocalDateTime.of(2026, 1, 12, 10, 0);
        dto.setCheckInTime(checkInTime);
        dto.setCheckOutTime(checkOutTime);

        Hotel dummyHotel = new Hotel.HotelBuilder()
                .withId(2L)
                .withName("Hotel Two")
                .build();
        Room room1 = new SingleRoom.Builder(120.0, new RoomIdentifier("B2", 2, "202"), dummyHotel)
                .withId(40L)
                .build();
        Room room2 = new SingleRoom.Builder(130.0, new RoomIdentifier("B2", 2, "203"), dummyHotel)
                .withId(41L)
                .build();
        List<Room> rooms = Arrays.asList(room1, room2);
        Guest guest1 = new Guest.GuestBuilder()
                .withId(30L)
                .withFirstName("Bob")
                .withLastName("Brown")
                .withBirthday(1980, 6, 15)
                .withEMail("bob@example.com")
                .withPhoneNumber("1111111111")
                .build();
        Guest guest2 = new Guest.GuestBuilder()
                .withId(31L)
                .withFirstName("Carol")
                .withLastName("White")
                .withBirthday(1985, 7, 20)
                .withEMail("carol@example.com")
                .withPhoneNumber("2222222222")
                .build();
        List<Guest> guests = Arrays.asList(guest1, guest2);

        Booking booking = BookingMapper.toDomain(dto, dummyHotel, rooms, guests);
        assertNotNull(booking);
        assertEquals(200L, booking.getId());
        assertEquals(dummyHotel, booking.getHotel());
        assertEquals(checkIn, booking.getCheckInDate());
        assertEquals(checkOut, booking.getCheckOutDate());
        assertTrue(booking.getStatus());
        assertEquals(500.0, booking.getTotalPrice(), 0.001);
        assertEquals(checkInTime, booking.getCheckInTime());
        assertEquals(checkOutTime, booking.getCheckOutTime());
        assertEquals(2, booking.getRooms().size());
        assertEquals(2, booking.getGuests().size());
    }
}
