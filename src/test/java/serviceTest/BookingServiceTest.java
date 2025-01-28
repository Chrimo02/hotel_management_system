package serviceTest;

import hotelmanagementsystem.domain.exceptions.BookingNotFoundException;
import hotelmanagementsystem.domain.exceptions.RoomNotFoundException;

import hotelmanagementsystem.domain.models.*;
import hotelmanagementsystem.domain.services.BookingService;
import hotelmanagementsystem.domain.services.GuestService;
import hotelmanagementsystem.domain.services.HotelService;
import hotelmanagementsystem.domain.services.RoomService;

import hotelmanagementsystem.infrastructure.persistence.repositories.interfaces.BookingRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class BookingServiceTest {

    private BookingService bookingService;
    private BookingRepository bookingRepository;
    private RoomService roomService;
    private HotelService hotelService;
    private GuestService guestService;

    @BeforeEach
    void setUp() {
        bookingRepository = mock(BookingRepository.class);
        roomService = mock(RoomService.class);
        hotelService = mock(HotelService.class);
        guestService = mock(GuestService.class);

        bookingService = new BookingService(bookingRepository, roomService, hotelService, guestService);
    }

    @Test
    void testMakeBooking() throws BookingNotFoundException, RoomNotFoundException {
        // Arrange
        long hotelId = 1L;
        LocalDate checkInDate = LocalDate.of(2024, 1, 1);
        LocalDate checkOutDate = LocalDate.of(2024, 1, 5);
        List<Class<? extends Room>> roomTypes = Arrays.asList(SingleRoom.class, DoubleRoom.class);
        List<Long> guestIds = Arrays.asList(1L, 2L);

        // Mock dependencies
        Hotel hotel = mock(Hotel.class);
        List<Room> rooms = Arrays.asList(mock(Room.class), mock(Room.class));
        List<Guest> guests = Arrays.asList(mock(Guest.class), mock(Guest.class));

        // Configure service mocks
        when(hotelService.getHotelByHotelId(hotelId)).thenReturn(hotel);
        when(roomService.findAvailableRooms(hotelId, roomTypes, checkInDate, checkOutDate)).thenReturn(rooms);
        when(guestService.loadGuests(guestIds)).thenReturn(guests);

        // Create and configure the mocked Booking object
        Booking mockBooking = mock(Booking.class);
        when(bookingRepository.createBooking(hotel, checkInDate, checkOutDate, rooms, guests)).thenReturn(mockBooking);

        // Act
        bookingService.makeBooking(hotelId, checkInDate, checkOutDate, roomTypes, guestIds);

        // Assert
        verify(bookingRepository).createBooking(hotel, checkInDate, checkOutDate, rooms, guests);
        verify(roomService).bookRooms(mockBooking);
    }

    @Test
    void testCancelBookingThrowsExceptionIfTooLate() {
        // Arrange
        long bookingId = 1L;
        Booking booking = mock(Booking.class);
        when(bookingRepository.getBookingById(bookingId)).thenReturn(booking);
        when(booking.getCheckInDate()).thenReturn(LocalDate.now().plusDays(1)); // Booking within cancellation period

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            bookingService.cancelBooking(bookingId);
        });
    }

    @Test
    void testCancelBooking() throws BookingNotFoundException, RoomNotFoundException {
        // Arrange
        long bookingId = 1L;
        Booking booking = mock(Booking.class);
        Room room = mock(Room.class);
        when(bookingRepository.getBookingById(bookingId)).thenReturn(booking);
        when(booking.getRooms()).thenReturn(Arrays.asList(room));
        when(booking.getCheckInDate()).thenReturn(LocalDate.now().plusDays(2)); // Cancellation within allowed period

        // Act
        bookingService.cancelBooking(bookingId);

        // Assert
        verify(roomService).cancelRoom(anyLong(), any(), any());
        verify(bookingRepository).updateBooking(booking);
    }

    @Test
    void testGuestCheckIn() throws BookingNotFoundException {
        // Arrange
        long bookingId = 1L;
        Booking booking = mock(Booking.class);
        when(bookingRepository.getBookingById(bookingId)).thenReturn(booking);

        // Act
        bookingService.guestCheckIn(bookingId);

        // Assert
        verify(booking).setCheckInTime(any());
        verify(bookingRepository).updateBooking(booking);
    }

    @Test
    void testGuestCheckOut() throws BookingNotFoundException {
        // Arrange
        long bookingId = 1L;
        Booking booking = mock(Booking.class);
        when(bookingRepository.getBookingById(bookingId)).thenReturn(booking);

        // Act
        bookingService.guestCheckOut(bookingId);

        // Assert
        verify(booking).setCheckOutTime(any());
        verify(bookingRepository).updateBooking(booking);
    }
}
