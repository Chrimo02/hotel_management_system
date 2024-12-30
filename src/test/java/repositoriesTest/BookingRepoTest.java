package repositoriesTest;

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
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookingRepoTest {

    @Mock
    private BookingDAO mockBookingDAO;

    @Mock
    private RoomMapper mockRoomMapper;

    @Mock
    private GuestMapper mockGuestMapper;

    @Mock
    private HotelMapper mockHotelMapper;

    @Mock
    private BookingMapper mockBookingMapper;

    private BookingDatabaseAdapter bookingAdapter;

    @BeforeEach
    void setUp() {
        bookingAdapter = new BookingDatabaseAdapter(mockBookingDAO, mockRoomMapper, mockGuestMapper, mockHotelMapper, mockBookingMapper);
    }

    @Test
    void testCreateBooking_Success() {
        Hotel hotel = mock(Hotel.class);
        LocalDate checkInDate = LocalDate.of(2024, 1, 1);
        LocalDate checkOutDate = LocalDate.of(2024, 1, 10);
        List<Room> rooms = mock(List.class);
        List<Guest> guests = mock(List.class);

        HotelEntity hotelEntity = mock(HotelEntity.class);
        List<RoomEntity> roomEntities = mock(List.class);
        List<GuestEntity> guestEntities = mock(List.class);
        BookingEntity bookingEntity = mock(BookingEntity.class);
        Booking expectedBooking = mock(Booking.class);

        when(mockHotelMapper.mapDomainHotelToHotelEntity(hotel)).thenReturn(hotelEntity);
        when(mockRoomMapper.toEntityList(rooms)).thenReturn(roomEntities);
        when(mockGuestMapper.guestsToGuestEntities(guests)).thenReturn(guestEntities);
        when(mockBookingDAO.create(any(BookingEntity.class))).thenReturn(bookingEntity);
        when(mockBookingMapper.bookingEntityToBooking(bookingEntity)).thenReturn(expectedBooking);

        Booking result = bookingAdapter.createBooking(hotel, checkInDate, checkOutDate, rooms, guests);

        assertNotNull(result);
        assertEquals(expectedBooking, result);
        verify(mockBookingDAO).create(any(BookingEntity.class));
    }

    @Test
    void testUpdateBooking_Success() {
        Booking booking = mock(Booking.class);
        BookingEntity bookingEntity = mock(BookingEntity.class);

        when(mockBookingDAO.read(booking.getId())).thenReturn(bookingEntity);

        bookingAdapter.updateBooking(booking);

        verify(bookingEntity).setStatus(booking.getStatus());
        verify(bookingEntity).setCheckInTime(booking.getCheckInTime());
        verify(bookingEntity).setCheckOutTime(booking.getCheckOutTime());
        verify(mockBookingDAO).update(bookingEntity);
    }

    @Test
    void testGetBookingById_Success() {
        long bookingId = 10L;
        BookingEntity bookingEntity = mock(BookingEntity.class);
        Booking expectedBooking = mock(Booking.class);

        when(mockBookingDAO.read(bookingId)).thenReturn(bookingEntity);
        when(mockBookingMapper.bookingEntityToBooking(bookingEntity)).thenReturn(expectedBooking);

        Booking result = bookingAdapter.getBookingById(bookingId);

        assertNotNull(result);
        assertEquals(expectedBooking, result);
        verify(mockBookingDAO).read(bookingId);
    }

    @Test
    void testCreateBooking_ThrowsException() {
        Hotel hotel = mock(Hotel.class);
        LocalDate checkInDate = LocalDate.of(2024, 1, 1);
        LocalDate checkOutDate = LocalDate.of(2024, 1, 10);
        List<Room> rooms = mock(List.class);
        List<Guest> guests = mock(List.class);

        when(mockBookingDAO.create(any(BookingEntity.class))).thenThrow(new RuntimeException("Simulated DAO error"));

        assertThrows(RuntimeException.class, () -> bookingAdapter.createBooking(hotel, checkInDate, checkOutDate, rooms, guests));

        verify(mockBookingDAO).create(any(BookingEntity.class));
    }

    @Test
    void testUpdateBooking_ThrowsException() {
        Booking booking = mock(Booking.class);

        when(mockBookingDAO.read(booking.getId())).thenThrow(new RuntimeException("Simulated DAO error"));

        assertThrows(RuntimeException.class, () -> bookingAdapter.updateBooking(booking));

        verify(mockBookingDAO).read(booking.getId());
    }
}
