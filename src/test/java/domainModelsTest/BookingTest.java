package domainModelsTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

class BookingTest {

    @Mock
    private Hotel mockHotel;

    @Mock
    private Room mockRoom;

    @Mock
    private Guest mockGuest;

    private Booking booking;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Mock the behavior of the Room and Guest
        when(mockRoom.getPricePerNight()).thenReturn(100.0);

        // Setup a mock hotel (although not directly used, it's a dependency in Booking)
        mockHotel = mock(Hotel.class);

        // Create a booking instance with mock data
        List<Room> rooms = new ArrayList<>(Collections.singletonList(mockRoom));
        List<Guest> guests = new ArrayList<>(Collections.singletonList(mockGuest));
        booking = new Booking(1L, mockHotel, LocalDate.of(2024, 12, 1), LocalDate.of(2024, 12, 5), rooms, guests, true, null, null);
    }

    @Test
    void testCalculateNights() {
        // Test calculateNights with mock dates
        long nights = Booking.calculateNights(LocalDate.of(2024, 12, 1), LocalDate.of(2024, 12, 5));
        assertEquals(4, nights, "The number of nights should be 4");
    }

    @Test
    void testCalculateTotalPrice() {
        // Test total price calculation
        double totalPrice = booking.getTotalPrice();
        assertEquals(400.0, totalPrice, "The total price should be 400.0");
    }

    @Test
    void testSetStatus() {
        // Test status change
        booking.setStatus(false);
        assertFalse(booking.getStatus(), "The booking status should be false after cancellation");
    }

    @Test
    void testIsCheckedIn() {
        // Test the booking check-in status
        assertFalse(booking.isCheckedIn(), "The booking should not be checked in initially");

        // Set a check-in time and test again
        booking.setCheckInTime(LocalDateTime.now());
        assertTrue(booking.isCheckedIn(), "The booking should be checked in after setting check-in time");
    }

    @Test
    void testIsCheckedOut() {
        // Test the booking check-out status
        assertFalse(booking.isCheckedOut(), "The booking should not be checked out initially");

        // Set a check-out time and test again
        booking.setCheckOutTime(LocalDateTime.now());
        assertTrue(booking.isCheckedOut(), "The booking should be checked out after setting check-out time");
    }

    @Test
    void testCompareTo() {
        // Test the compareTo method based on check-in dates
        Booking laterBooking = new Booking(2L, mockHotel, LocalDate.of(2024, 12, 2), LocalDate.of(2024, 12, 6), new ArrayList<>(), new ArrayList<>(), true, LocalDateTime.now(), null);
        assertTrue(booking.compareTo(laterBooking) < 0, "The first booking should be before the second one based on check-in date");
    }

    @Test
    void testCalculateTotalPriceWithMultipleRooms() {
        // Test price calculation with multiple rooms
        Room mockSecondRoom = mock(Room.class);
        when(mockSecondRoom.getPricePerNight()).thenReturn(150.0);
        List<Room> multipleRooms = new ArrayList<>();
        multipleRooms.add(mockRoom);  // mockRoom's price is 100.0 per night
        multipleRooms.add(mockSecondRoom);  // mockSecondRoom's price is 150.0 per night

        Booking bookingWithMultipleRooms = new Booking(2L, mockHotel, LocalDate.of(2024, 12, 1), LocalDate.of(2024, 12, 5), multipleRooms, new ArrayList<>(), true, LocalDateTime.now(), null);
        double totalPrice = bookingWithMultipleRooms.getTotalPrice();

        // Correct total price calculation: (100 * 4) + (150 * 4) = 1000.0
        assertEquals(1000.0, totalPrice, "The total price should be 1000.0 for two rooms over 4 nights");
    }


    @Test
    void testBookingStatusOnCreation() {
        // Test that booking is confirmed when created
        assertTrue(booking.getStatus(), "The booking status should be true (active) when created");
    }
}
