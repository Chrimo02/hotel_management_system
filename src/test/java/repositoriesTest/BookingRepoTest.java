package repositoriesTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import hotelmanagementsystem.domain.models.Booking;
import hotelmanagementsystem.domain.models.Guest;
import hotelmanagementsystem.domain.models.Hotel;
import hotelmanagementsystem.domain.models.Room;
import hotelmanagementsystem.infrastructure.persistence.dao.interfaces.BookingDAO;
import hotelmanagementsystem.infrastructure.persistence.entities.BookingEntity;
import hotelmanagementsystem.infrastructure.persistence.entities.GuestEntity;
import hotelmanagementsystem.infrastructure.persistence.entities.HotelEntity;
import hotelmanagementsystem.infrastructure.persistence.entities.RoomEntity;
import hotelmanagementsystem.infrastructure.persistence.mapper.BookingMapper;
import hotelmanagementsystem.infrastructure.persistence.mapper.GuestMapper;
import hotelmanagementsystem.infrastructure.persistence.mapper.HotelMapper;
import hotelmanagementsystem.infrastructure.persistence.mapper.RoomMapper;
import hotelmanagementsystem.infrastructure.persistence.repositories.adapters.BookingDatabaseAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BookingRepoTest {

    @Mock
    private BookingDAO bookingDAO;
    @Mock
    private RoomMapper roomMapper;
    @Mock
    private GuestMapper guestMapper;
    @Mock
    private HotelMapper hotelMapper;
    @Mock
    private BookingMapper bookingMapper;

    @InjectMocks
    private BookingDatabaseAdapter adapter;

    private Hotel dummyHotel;
    private Room dummyRoom;
    private Guest dummyGuest;
    private Booking dummyBooking;

    private HotelEntity dummyHotelEntity;
    private RoomEntity dummyRoomEntity;
    private GuestEntity dummyGuestEntity;
    private BookingEntity dummyBookingEntity;

    @BeforeEach
    public void setUp() {
        dummyHotel = new Hotel.HotelBuilder()
                .withId(1L)
                .withName("Test Hotel")
                .build();

        dummyRoom = mock(Room.class);
        lenient().when(dummyRoom.getId()).thenReturn(10L);

        dummyGuest = new Guest.GuestBuilder()
                .withId(20L)
                .withFirstName("Alice")
                .withLastName("Smith")
                .withBirthday(1990, 1, 1)
                .withEMail("alice@example.com")
                .withPhoneNumber("123456789")
                .build();

        dummyBooking = new Booking(
                100L,
                dummyHotel,
                LocalDate.of(2025, 12, 1),
                LocalDate.of(2025, 12, 5),
                Collections.singletonList(dummyRoom),
                Collections.singletonList(dummyGuest),
                true,
                LocalDateTime.of(2025, 12, 1, 15, 0),
                LocalDateTime.of(2025, 12, 5, 11, 0)
        );

        dummyHotelEntity = new HotelEntity.HotelBuilder()
                .withId(dummyHotel.getId())
                .withName(dummyHotel.getName())
                .build();

        dummyRoomEntity = mock(RoomEntity.class);
        lenient().when(dummyRoomEntity.getId()).thenReturn(10L);

        dummyGuestEntity = new GuestEntity("Alice", "Smith", 1990, 1, 1, "alice@example.com", "123456789");
        dummyGuestEntity.setId(dummyGuest.getId());

        dummyBookingEntity = mock(BookingEntity.class);
        lenient().when(dummyBookingEntity.getId()).thenReturn(100L);
    }

    @Test
    void testCreateBooking() {
        when(roomMapper.toEntityList(any())).thenReturn(Collections.singletonList(dummyRoomEntity));
        when(guestMapper.guestsToGuestEntities(any())).thenReturn(Collections.singletonList(dummyGuestEntity));
        when(hotelMapper.mapDomainHotelToHotelEntity(any())).thenReturn(dummyHotelEntity);
        when(bookingDAO.create(any())).thenReturn(dummyBookingEntity);
        when(bookingMapper.bookingEntityToBooking(any())).thenReturn(dummyBooking);

        Booking result = adapter.createBooking(
                dummyHotel,
                dummyBooking.getCheckInDate(),
                dummyBooking.getCheckOutDate(),
                dummyBooking.getRooms(),
                dummyBooking.getGuests()
        );

        assertNotNull(result);
        assertEquals(100L, result.getId());
        verify(bookingDAO).create(any());
    }

    @Test
    void testUpdateBooking() {
        when(bookingDAO.read(100L)).thenReturn(dummyBookingEntity);

        adapter.updateBooking(dummyBooking);

        verify(bookingDAO).update(dummyBookingEntity);
        verify(dummyBookingEntity).setRooms(any());
        verify(dummyBookingEntity).setStatus(true);
        verify(dummyBookingEntity).setCheckInTime(dummyBooking.getCheckInTime());
        verify(dummyBookingEntity).setCheckOutTime(dummyBooking.getCheckOutTime());
    }

    @Test
    void testGetBookingById() {
        when(bookingDAO.read(100L)).thenReturn(dummyBookingEntity);
        when(dummyBookingEntity.getHotel()).thenReturn(dummyHotelEntity);
        when(dummyBookingEntity.getRooms()).thenReturn(Collections.singletonList(dummyRoomEntity));
        when(dummyBookingEntity.getGuests()).thenReturn(Collections.singletonList(dummyGuestEntity));
        when(dummyBookingEntity.getCheckInDate()).thenReturn(dummyBooking.getCheckInDate());
        when(dummyBookingEntity.getCheckOutDate()).thenReturn(dummyBooking.getCheckOutDate());

        when(roomMapper.toDomainList(any())).thenReturn(Collections.singletonList(dummyRoom));
        when(guestMapper.guestEntitiesToGuests(any())).thenReturn(Collections.singletonList(dummyGuest));
        when(hotelMapper.mapHotelEntityToDomainHotel(any())).thenReturn(dummyHotel);

        Booking result = adapter.getBookingById(100L);

        assertNotNull(result);
        assertEquals(100L, result.getId());
        assertEquals(1, result.getRooms().size());
    }

    @Test
    void testFindBookingsByCheckInDate() {
        LocalDate targetDate = LocalDate.of(2025, 12, 1);
        when(bookingDAO.findBookingsByCheckInDate(targetDate))
                .thenReturn(Collections.singletonList(dummyBookingEntity));
        when(bookingMapper.bookingEntityToBooking(dummyBookingEntity))
                .thenReturn(dummyBooking);

        List<Booking> results = adapter.findBookingsByCheckInDate(targetDate);

        assertEquals(1, results.size());
        assertEquals(100L, results.get(0).getId());
    }
}