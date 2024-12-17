package domainModelsTest;


import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.Booking;
import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.Guest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GuestTest {

    private Guest guest;

    @Mock
    private Booking mockBooking1;

    @Mock
    private Booking mockBooking2;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        guest = new Guest.GuestBuilder()
                .withId(1L)
                .withFirstName("John")
                .withLastName("Doe")
                .withTitle("Mr.")
                .withBirthday(1990, 5, 20)
                .withEMail("john.doe@example.com")
                .withPhoneNumber("1234567890")
                .build();
    }

    @Test
    public void testGuestBuilder_CreatesGuestWithCorrectAttributes() {
        assertEquals(1L, guest.getId());
        assertEquals("John", guest.getFirstName());
        assertEquals("Doe", guest.getLastName());
        assertEquals("Mr.", guest.getTitle());
        assertEquals(LocalDate.of(1990, 5, 20), guest.getBirthday());
        assertEquals("john.doe@example.com", guest.geteMail());
        assertEquals("1234567890", guest.getPhoneNumber());
        assertTrue(guest.getBookingsHistory().isEmpty());
    }

    @Test
    public void testSetEmail_UpdatesEmailCorrectly() {
        guest.seteMail("new.email@example.com");
        assertEquals("new.email@example.com", guest.geteMail());
    }

    @Test
    public void testSetPhoneNumber_UpdatesPhoneNumberCorrectly() {
        guest.setPhoneNumber("0987654321");
        assertEquals("0987654321", guest.getPhoneNumber());
    }

    @Test
    public void testSetLastName_UpdatesLastNameCorrectly() {
        guest.setLastName("Smith");
        assertEquals("Smith", guest.getLastName());
    }

    @Test
    public void testSetTitle_UpdatesTitleCorrectly() {
        guest.setTitle("Dr.");
        assertEquals("Dr.", guest.getTitle());
    }

    @Test
    public void testBookingsHistory_AddBookingsUsingMocks() {
        // Mocked Bookings
        List<Booking> mockedBookings = new ArrayList<>();
        mockedBookings.add(mockBooking1);
        mockedBookings.add(mockBooking2);

        guest.getBookingsHistory().addAll(mockedBookings);

        List<Booking> bookings = guest.getBookingsHistory();
        assertEquals(2, bookings.size());
        assertTrue(bookings.contains(mockBooking1));
        assertTrue(bookings.contains(mockBooking2));

        // Verify no methods are called on mock objects (isolated)
        verifyNoInteractions(mockBooking1, mockBooking2);
    }

    @Test
    public void testBookingsHistory_AddBookingDoesNotModifyExternalList() {
        List<Booking> externalBookings = new ArrayList<>();
        externalBookings.add(mockBooking1);

        guest = new Guest.GuestBuilder()
                .withBookingsHistory(externalBookings)
                .build();

        // Add another booking internally
        guest.getBookingsHistory().add(mockBooking2);

        assertEquals(2, guest.getBookingsHistory().size());
        assertTrue(guest.getBookingsHistory().contains(mockBooking1));
        assertTrue(guest.getBookingsHistory().contains(mockBooking2));

        // Verify external list remains unchanged
        assertEquals(1, externalBookings.size());
        assertTrue(externalBookings.contains(mockBooking1));
    }

    @Test
    public void testGuestBuilder_WithOptionalFieldsAndMockedBookings() {
        List<Booking> mockedBookings = new ArrayList<>();
        mockedBookings.add(mockBooking1);

        Guest guestWithBookings = new Guest.GuestBuilder()
                .withId(2L)
                .withFirstName("Jane")
                .withBookingsHistory(mockedBookings)
                .build();

        assertEquals(2L, guestWithBookings.getId());
        assertEquals("Jane", guestWithBookings.getFirstName());
        assertEquals(1, guestWithBookings.getBookingsHistory().size());
        assertTrue(guestWithBookings.getBookingsHistory().contains(mockBooking1));
    }

    @Test
    public void testGuestBuilder_NullBookingsHistoryIsHandledGracefully() {
        Guest guestWithNullBookings = new Guest.GuestBuilder()
                .withFirstName("Jane")
                .withBookingsHistory(null)
                .build();

        assertNotNull(guestWithNullBookings.getBookingsHistory());
        assertTrue(guestWithNullBookings.getBookingsHistory().isEmpty());
    }

    @Test
    public void testBuilder_WhenCreatingMinimalGuest_OptionalFieldsRemainNull() {
        Guest minimalGuest = new Guest.GuestBuilder()
                .withFirstName("Minimal")
                .build();

        assertEquals("Minimal", minimalGuest.getFirstName());
        assertNull(minimalGuest.getLastName());
        assertNull(minimalGuest.getTitle());
        assertNull(minimalGuest.getBirthday());
        assertNull(minimalGuest.geteMail());
        assertNull(minimalGuest.getPhoneNumber());
        assertTrue(minimalGuest.getBookingsHistory().isEmpty());
    }
}
