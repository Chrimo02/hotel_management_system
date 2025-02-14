package mapperTestsPersistenceDomain;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;

import hotelmanagementsystem.domain.models.Booking;
import hotelmanagementsystem.domain.models.Guest;
import hotelmanagementsystem.domain.models.Hotel;
import hotelmanagementsystem.domain.models.Room;
import hotelmanagementsystem.domain.models.SingleRoom;
import hotelmanagementsystem.domain.models.RoomIdentifier;
import hotelmanagementsystem.infrastructure.persistence.entities.BookingEntity;
import hotelmanagementsystem.infrastructure.persistence.entities.GuestEntity;
import hotelmanagementsystem.infrastructure.persistence.entities.HotelEntity;
import hotelmanagementsystem.infrastructure.persistence.entities.RoomEntity;
import hotelmanagementsystem.infrastructure.persistence.entities.SingleRoomEntity;
import hotelmanagementsystem.infrastructure.persistence.entities.RoomIdentifierEntity;
import hotelmanagementsystem.infrastructure.persistence.mapper.BookingMapper;
import hotelmanagementsystem.infrastructure.persistence.mapper.GuestMapper;
import hotelmanagementsystem.infrastructure.persistence.mapper.RoomIdentifierMapper;
import hotelmanagementsystem.infrastructure.persistence.mapper.RoomMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BookingMapperTest {

    @Mock
    private GuestMapper guestMapper;

    @Mock
    private RoomMapper roomMapper;


    private BookingMapper bookingMapper;

    private Hotel dummyHotel;
    private Room dummyRoom;
    private Guest dummyGuest;
    private Booking dummyBooking;

    private GuestEntity dummyGuestEntity;
    private RoomEntity dummyRoomEntity;

    @BeforeEach
    public void setUp() {
        bookingMapper = new BookingMapper(guestMapper, roomMapper);

        dummyHotel = new Hotel.HotelBuilder()
                .withId(1L)
                .withName("Test Hotel")
                .build();

        RoomIdentifier roomId = new RoomIdentifier("Building1", 1, "101");
        dummyRoom = new SingleRoom.Builder(100.0, roomId, dummyHotel)
                .withId(10L)
                .build();

        dummyGuest = new Guest.GuestBuilder()
                .withId(20L)
                .withFirstName("John")
                .withLastName("Doe")
                .withBirthday(1990, 1, 1)
                .withEMail("john@example.com")
                .withPhoneNumber("123456")
                .build();

        dummyBooking = new Booking(
                100L,
                dummyHotel,
                LocalDate.of(2025, 12, 1),
                LocalDate.of(2025, 12, 5),
                Collections.singletonList(dummyRoom),
                Collections.singletonList(dummyGuest),
                true,
                null,
                null
        );

        dummyGuestEntity = new GuestEntity("John", "Doe", 1990, 1, 1, "john@example.com", "123456");
        dummyGuestEntity.setId(20L);

        RoomIdentifierEntity riEntity = new RoomIdentifierEntity("Building1", 1, "101");
        HotelEntity dummyHotelEntity = new HotelEntity.HotelBuilder()
                .withId(1L)
                .withName("Test Hotel")
                .build();
        dummyRoomEntity = new SingleRoomEntity(10L, 100.0, riEntity, dummyHotelEntity);
    }

    @Test
    public void testBookingToBookingEntity() {
        when(guestMapper.guestsToGuestEntities(dummyBooking.getGuests()))
                .thenReturn(Collections.singletonList(dummyGuestEntity));
        when(roomMapper.toEntityList(dummyBooking.getRooms()))
                .thenReturn(Collections.singletonList(dummyRoomEntity));

        BookingEntity entity = bookingMapper.bookingToBookingEntity(dummyBooking);
        assertNotNull(entity);
        assertEquals(dummyBooking.getId(), entity.getId());
        assertEquals(dummyBooking.getCheckInDate(), entity.getCheckInDate());
        assertEquals(dummyBooking.getCheckOutDate(), entity.getCheckOutDate());
        assertEquals(dummyBooking.getStatus(), entity.isStatus());
        assertEquals(1, entity.getGuests().size());
        assertEquals(1, entity.getRooms().size());
    }

    @Test
    public void testBookingEntityToBooking() {
        when(roomMapper.toMinimalDomain(any(RoomEntity.class))).thenReturn(dummyRoom);
        when(guestMapper.guestEntitiesToGuests(anyList())).thenReturn(Collections.singletonList(dummyGuest));

        HotelEntity hotelEntity = new HotelEntity.HotelBuilder()
                .withId(1L)
                .withName("Test Hotel")
                .build();
        BookingEntity entity = new BookingEntity(
                100L,
                hotelEntity,
                LocalDate.of(2025, 12, 1),
                LocalDate.of(2025, 12, 5),
                Collections.singletonList(dummyRoomEntity),
                Collections.singletonList(dummyGuestEntity),
                true,
                null,
                null
        );

        Booking booking = bookingMapper.bookingEntityToBooking(entity);
        assertNotNull(booking);
        assertEquals(entity.getId(), booking.getId());
        assertEquals(entity.getCheckInDate(), booking.getCheckInDate());
        assertEquals(entity.getCheckOutDate(), booking.getCheckOutDate());
        assertTrue(booking.getStatus());
        assertEquals(1, booking.getRooms().size(), "Die Room-Liste sollte genau 1 Element enthalten");
        assertEquals(1, booking.getGuests().size(), "Die Guest-Liste sollte genau 1 Element enthalten");
    }


}
