package domainModelsTest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import hotelmanagementsystem.domain.models.Booking;
import hotelmanagementsystem.domain.models.Guest;
import hotelmanagementsystem.domain.models.Hotel;
import hotelmanagementsystem.domain.models.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BookingTest {

    @Mock
    private Room room1;

    @Mock
    private Room room2;

    @Mock
    private Hotel dummyHotel;

    @Mock
    private Guest dummyGuest;

    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;

    private Booking booking;

    @BeforeEach
    public void setUp() {
        // Setze Testdaten: Check-in am 1.12.2024, Check-out am 5.12.2024 (4 Nächte)
        checkInDate = LocalDate.of(2024, 12, 1);
        checkOutDate = LocalDate.of(2024, 12, 5);
        // Beispiel Check-In/Out Zeiten
        checkInTime = LocalDateTime.of(2024, 12, 1, 14, 0);
        checkOutTime = LocalDateTime.of(2024, 12, 5, 11, 0);

        // Stub: Gib für die Rooms einen Preis zurück
        when(room1.getPricePerNight()).thenReturn(100.0);
        when(room2.getPricePerNight()).thenReturn(150.0);

        // Erstelle eine Booking-Instanz mit zwei Räumen und einem Gast
        List<Room> rooms = Arrays.asList(room1, room2);
        List<Guest> guests = Collections.singletonList(dummyGuest);
        // Status initial true, CheckIn/Out Zeiten zunächst null
        booking = new Booking(1L, dummyHotel, checkInDate, checkOutDate, rooms, guests, true, null, null);
    }

    @Test
    public void testCalculateNights_ValidDates() {
        long nights = Booking.calculateNights(checkInDate, checkOutDate);
        assertEquals(4, nights, "Es sollten 4 Nächte berechnet werden");
    }

    @Test
    public void testCalculateNights_NullDates() {
        assertThrows(IllegalArgumentException.class, () -> Booking.calculateNights(null, checkOutDate),
                "Null als Check-in-Datum sollte eine Exception auslösen");
        assertThrows(IllegalArgumentException.class, () -> Booking.calculateNights(checkInDate, null),
                "Null als Check-out-Datum sollte eine Exception auslösen");
    }

    @Test
    public void testCalculateTotalPrice() {
        // Berechnung: (100 + 150) * 4 Nächte = 1000
        double totalPrice = booking.getTotalPrice();
        assertEquals(1000.0, totalPrice, 0.001, "Der Gesamtpreis sollte 1000.0 betragen");
    }

    @Test
    public void testStatusSetterGetter() {
        // Anfangs sollte der Status true sein
        assertTrue(booking.getStatus(), "Der initiale Status sollte true sein");
        booking.setStatus(false);
        assertFalse(booking.getStatus(), "Nach setStatus(false) sollte false zurückgegeben werden");
    }

    @Test
    public void testCheckInAndCheckOutTimes() {
        // Ohne gesetzte Zeiten sollten isCheckedIn und isCheckedOut false zurückgeben
        assertFalse(booking.isCheckedIn(), "Ohne Check-in-Zeit sollte isCheckedIn false sein");
        assertFalse(booking.isCheckedOut(), "Ohne Check-out-Zeit sollte isCheckedOut false sein");

        booking.setCheckInTime(checkInTime);
        booking.setCheckOutTime(checkOutTime);
        assertTrue(booking.isCheckedIn(), "Nach Setzen der CheckInTime sollte isCheckedIn true sein");
        assertTrue(booking.isCheckedOut(), "Nach Setzen der CheckOutTime sollte isCheckedOut true sein");
    }

    @Test
    public void testCompareTo() {
        // Erstelle eine zweite Booking mit einem späteren Check-in-Datum
        LocalDate laterCheckIn = LocalDate.of(2024, 12, 10);
        Booking laterBooking = new Booking(2L, dummyHotel, laterCheckIn, laterCheckIn.plusDays(3),
                booking.getRooms(), booking.getGuests(), true, null, null);
        assertTrue(booking.compareTo(laterBooking) < 0, "Buchung 1 sollte vor Buchung 2 liegen");
        assertTrue(laterBooking.compareTo(booking) > 0, "Buchung 2 sollte nach Buchung 1 liegen");
    }
}


