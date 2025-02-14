package serviceTest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import hotelmanagementsystem.domain.exceptions.BookingNotFoundException;
import hotelmanagementsystem.domain.exceptions.HotelNotFoundException;
import hotelmanagementsystem.domain.exceptions.RoomNotFoundException;
import hotelmanagementsystem.domain.models.Booking;
import hotelmanagementsystem.domain.models.Guest;
import hotelmanagementsystem.domain.models.Hotel;
import hotelmanagementsystem.domain.models.Room;
import hotelmanagementsystem.domain.services.BookingService;
import hotelmanagementsystem.domain.services.GuestService;
import hotelmanagementsystem.domain.services.HotelService;
import hotelmanagementsystem.domain.services.RoomService;
import hotelmanagementsystem.infrastructure.persistence.repositories.interfaces.BookingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BookingServiceTest {

    @InjectMocks
    private BookingService bookingService;

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private RoomService roomService;

    @Mock
    private HotelService hotelService;

    @Mock
    private GuestService guestService;

    private Hotel dummyHotel;
    private List<Room> dummyRooms;
    private List<Guest> dummyGuests;
    private Booking dummyBooking;
    private LocalDate checkIn;
    private LocalDate checkOut;

    @BeforeEach
    public void setUp() {
        checkIn = LocalDate.now().plusDays(2);
        checkOut = checkIn.plusDays(3);
        dummyHotel = mock(Hotel.class);
        Room dummyRoom = mock(Room.class);
        when(dummyRoom.getPricePerNight()).thenReturn(100.0);
        dummyRooms = Collections.singletonList(dummyRoom);
        Guest dummyGuest = mock(Guest.class);
        dummyGuests = Collections.singletonList(dummyGuest);
        dummyBooking = new Booking(1L, dummyHotel, checkIn, checkOut, dummyRooms, dummyGuests, true, null, null);
    }

    @Test
    public void testMakeBooking_Success() throws HotelNotFoundException {
        List<Class<? extends Room>> roomTypes = Collections.singletonList(Room.class);
        when(roomService.findAvailableRooms(anyLong(), anyList(), eq(checkIn), eq(checkOut))).thenReturn(dummyRooms);
        when(guestService.loadGuests(anyList())).thenReturn(dummyGuests);
        when(hotelService.getHotelByHotelId(anyLong())).thenReturn(dummyHotel);
        when(bookingRepository.createBooking(dummyHotel, checkIn, checkOut, dummyRooms, dummyGuests))
                .thenReturn(dummyBooking);

        Booking result = bookingService.makeBooking(1L, checkIn, checkOut, roomTypes, Arrays.asList(1L));
        assertEquals(dummyBooking, result);
        verify(roomService).bookRooms(dummyBooking);
    }

    @Test
    public void testMakeBooking_InvalidCheckInDate() {
        LocalDate invalidCheckIn = LocalDate.now();
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> bookingService.makeBooking(1L, invalidCheckIn, checkOut, Collections.singletonList(Room.class), Arrays.asList(1L)));
        assertTrue(ex.getMessage().contains("CheckInDate must be after LocalDate.now"));
    }

    @Test
    public void testMakeBooking_CheckInNotBeforeCheckOut() {
        LocalDate invalidCheckIn = LocalDate.now().plusDays(5);
        LocalDate invalidCheckOut = LocalDate.now().plusDays(3);
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> bookingService.makeBooking(1L, invalidCheckIn, invalidCheckOut, Collections.singletonList(Room.class), Arrays.asList(1L)));
        assertTrue(ex.getMessage().contains("CheckInDate must be before CheckoutDate"));
    }

    @Test
    public void testCancelBooking_Success() throws BookingNotFoundException, RoomNotFoundException {
        // Buchung mit CheckIn in mehr als 2 Tagen
        Booking bookingToCancel = new Booking(2L, dummyHotel, LocalDate.now().plusDays(3), LocalDate.now().plusDays(6), dummyRooms, dummyGuests, true, null, null);
        when(bookingRepository.getBookingById(2L)).thenReturn(bookingToCancel);
        doNothing().when(roomService).cancelRoom(anyLong(), eq(bookingToCancel));

        bookingService.cancelBooking(2L);
        assertFalse(bookingToCancel.getStatus(), "Booking status should be false after cancellation");
        assertTrue(bookingToCancel.getRooms().isEmpty(), "Rooms list should be empty after cancellation");
        verify(bookingRepository).updateBooking(bookingToCancel);
    }

    @Test
    public void testCancelBooking_TooLate() {
        // Buchung mit CheckIn in weniger als 2 Tagen
        Booking bookingToCancel = new Booking(3L, dummyHotel, LocalDate.now().plusDays(1), LocalDate.now().plusDays(3), dummyRooms, dummyGuests, true, null, null);
        when(bookingRepository.getBookingById(3L)).thenReturn(bookingToCancel);
        Exception ex = assertThrows(RuntimeException.class, () -> bookingService.cancelBooking(3L));
        assertTrue(ex.getMessage().contains("cancellation deadline has already expired"));
    }

    @Test
    public void testGuestCheckIn_Success() throws BookingNotFoundException {
        // Buchung, bei der CheckIn erlaubt ist (heute ist nicht mehr als 1 Tag vor CheckIn)
        Booking bookingForCheckIn = new Booking(4L, dummyHotel, LocalDate.now().plusDays(1), LocalDate.now().plusDays(3), dummyRooms, dummyGuests, true, null, null);
        when(bookingRepository.getBookingById(4L)).thenReturn(bookingForCheckIn);
        assertDoesNotThrow(() -> bookingService.guestCheckIn(4L));
        verify(bookingRepository).updateBooking(bookingForCheckIn);
        assertNotNull(bookingForCheckIn.getCheckInTime(), "CheckInTime should be set after guestCheckIn");
    }

    @Test
    public void testGuestCheckIn_TooEarly() {
        Booking bookingForCheckIn = new Booking(5L, dummyHotel, LocalDate.now().plusDays(3), LocalDate.now().plusDays(5), dummyRooms, dummyGuests, true, null, null);
        when(bookingRepository.getBookingById(5L)).thenReturn(bookingForCheckIn);
        Exception ex = assertThrows(IllegalStateException.class, () -> bookingService.guestCheckIn(5L));
        assertTrue(ex.getMessage().contains("cannot check in more than one day before"));
    }

    @Test
    public void testGuestCheckOut_Success() throws BookingNotFoundException {
        Booking bookingForCheckOut = new Booking(6L, dummyHotel, LocalDate.now().minusDays(1), LocalDate.now().plusDays(1), dummyRooms, dummyGuests, true, LocalDateTime.now().minusHours(1), null);
        when(bookingRepository.getBookingById(6L)).thenReturn(bookingForCheckOut);
        assertDoesNotThrow(() -> bookingService.guestCheckOut(6L));
        verify(bookingRepository).updateBooking(bookingForCheckOut);
        assertNotNull(bookingForCheckOut.getCheckOutTime(), "CheckOutTime should be set after guestCheckOut");
    }

    @Test
    public void testGuestCheckOut_NotCheckedIn() {
        Booking bookingForCheckOut = new Booking(7L, dummyHotel, LocalDate.now().minusDays(1), LocalDate.now().plusDays(1), dummyRooms, dummyGuests, true, null, null);
        when(bookingRepository.getBookingById(7L)).thenReturn(bookingForCheckOut);
        Exception ex = assertThrows(IllegalStateException.class, () -> bookingService.guestCheckOut(7L));
        assertTrue(ex.getMessage().contains("cannot check out without checking in first"));
    }

    @Test
    public void testGetBookingById() throws BookingNotFoundException {
        when(bookingRepository.getBookingById(1L)).thenReturn(dummyBooking);
        Booking result = bookingService.getBookingById(1L);
        assertEquals(dummyBooking, result);
    }

    @Test
    public void testFindAllByCheckInDate() {
        LocalDate targetDate = LocalDate.now().plusDays(5);
        List<Booking> bookings = Collections.singletonList(dummyBooking);
        when(bookingRepository.findBookingsByCheckInDate(targetDate)).thenReturn(bookings);
        List<Booking> result = bookingService.findAllByCheckInDate(targetDate);
        assertEquals(bookings, result);
    }
}
