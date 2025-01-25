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

        // Mock BookingEntity and its dependencies
        BookingEntity bookingEntity = mock(BookingEntity.class);
        HotelEntity mockHotelEntity = mock(HotelEntity.class);
        RoomEntity mockRoomEntity = mock(RoomEntity.class);
        GuestEntity mockGuestEntity = mock(GuestEntity.class);

        // Create lists for rooms and guests
        List<RoomEntity> roomEntities = List.of(mockRoomEntity);
        List<GuestEntity> guestEntities = List.of(mockGuestEntity);

        // Define behaviors for BookingEntity getters to return valid, non-null data
        when(mockBookingDAO.read(bookingId)).thenReturn(bookingEntity);
        when(bookingEntity.getId()).thenReturn(bookingId);
        when(bookingEntity.getCheckInDate()).thenReturn(LocalDate.of(2025, 3, 1));    // Valid check-in date
        when(bookingEntity.getCheckOutDate()).thenReturn(LocalDate.of(2025, 3, 5));   // Valid check-out date
        when(bookingEntity.getHotel()).thenReturn(mockHotelEntity);
        when(bookingEntity.getRooms()).thenReturn(roomEntities);
        when(bookingEntity.getGuests()).thenReturn(guestEntities);
        when(bookingEntity.isStatus()).thenReturn(true);
        when(bookingEntity.getCheckInTime()).thenReturn(null);
        when(bookingEntity.getCheckOutTime()).thenReturn(null);

        // Mock mappers to return valid domain objects
        Hotel mockHotel = mock(Hotel.class);
        List<Room> rooms = List.of(mock(Room.class));
        List<Guest> guests = List.of(mock(Guest.class));

        when(mockHotelMapper.mapHotelEntityToDomainHotel(mockHotelEntity)).thenReturn(mockHotel);
        when(mockRoomMapper.toDomainList(roomEntities)).thenReturn(rooms);
        when(mockGuestMapper.guestEntitiesToGuests(guestEntities)).thenReturn(guests);

        // Run the method under test
        Booking result = bookingAdapter.getBookingById(bookingId);

        // Create the expected Booking object with the same data
        Booking expectedBooking = new Booking(
                bookingId,
                mockHotel,
                LocalDate.of(2025, 3, 1),
                LocalDate.of(2025, 3, 5),
                rooms,
                guests,
                true,
                null,
                null
        );

        // Assertions to verify that the result matches the expected Booking
        assertNotNull(result, "The returned Booking should not be null");
        assertEquals(expectedBooking.getId(), result.getId(), "Booking IDs should match");
        assertEquals(expectedBooking.getCheckInDate(), result.getCheckInDate(), "Check-in dates should match");
        assertEquals(expectedBooking.getCheckOutDate(), result.getCheckOutDate(), "Check-out dates should match");
        assertEquals(expectedBooking.getHotel(), result.getHotel(), "Hotels should match");
        assertEquals(expectedBooking.getRooms(), result.getRooms(), "Rooms should match");
        assertEquals(expectedBooking.getGuests(), result.getGuests(), "Guests should match");
        assertEquals(expectedBooking.getStatus(), result.getStatus(), "Statuses should match");
        assertEquals(expectedBooking.getCheckInTime(), result.getCheckInTime(), "Check-in times should match");
        assertEquals(expectedBooking.getCheckOutTime(), result.getCheckOutTime(), "Check-out times should match");

        // Verify that the mocked methods were called as expected
        verify(mockBookingDAO).read(bookingId);
        verify(mockRoomMapper).toDomainList(roomEntities);
        verify(mockGuestMapper).guestEntitiesToGuests(guestEntities);
        verify(mockHotelMapper).mapHotelEntityToDomainHotel(mockHotelEntity);
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
