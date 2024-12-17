package domainModelsTest;
import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.Booking;
import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.Guest;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class GuestTest {
    @Test
    public void testGuestBuilderCreatesGuestCorrectly() {
        Guest guest = new Guest.GuestBuilder()
                .withFirstName("Max")
                .withLastName("Mustermann")
                .withTitle("Dr.")
                .withBirthday(2000, 1, 1)
                .withEMail("drmax@mail.com")
                .withPhoneNumber("12345")
                .build();

        assertEquals("Max", guest.getFirstName());
        assertEquals("Mustermann", guest.getLastName());
        assertEquals("Dr.", guest.getTitle());
        assertEquals(LocalDate.of(2000, 1, 1), guest.getBirthday());
        assertEquals("drmax@mail.com", guest.geteMail());
        assertEquals("12345", guest.getPhoneNumber());
        assertTrue(guest.getBookingsHistory().isEmpty());
    }

    @Test
    public void testSettersUpdateGuestCorrectly() {
        Guest guest = new Guest.GuestBuilder()
                .withFirstName("John")
                .withLastName("Doe")
                .build();

        guest.setLastName("Smith");
        guest.setTitle("Mr.");
        guest.seteMail("new.email@example.com");
        guest.setPhoneNumber("98765");
        guest.setBookingsHistory(new ArrayList<>());

        assertEquals("Smith", guest.getLastName());
        assertEquals("Mr.", guest.getTitle());
        assertEquals("new.email@example.com", guest.geteMail());
        assertEquals("98765", guest.getPhoneNumber());
        assertTrue(guest.getBookingsHistory().isEmpty());
    }

    @Test
    public void testBookingsHistoryInitiallyEmpty() {
        Guest guest = new Guest.GuestBuilder()
                .withFirstName("John")
                .build();

        assertNotNull(guest.getBookingsHistory());
        assertTrue(guest.getBookingsHistory().isEmpty());
    }

    @Test
    public void testAddBookingToBookingsHistory() {
        Guest guest = new Guest.GuestBuilder().withFirstName("John").build();
        Booking mockedBooking = org.mockito.Mockito.mock(Booking.class);

        guest.getBookingsHistory().add(mockedBooking);

        assertEquals(1, guest.getBookingsHistory().size());
        assertSame(mockedBooking, guest.getBookingsHistory().get(0));
    }

    @Test
    public void testSetBookingsHistory() {
        Guest guest = new Guest.GuestBuilder().withFirstName("John").build();
        List<Booking> bookings = new ArrayList<>();

        Booking mockedBooking1 = org.mockito.Mockito.mock(Booking.class);
        Booking mockedBooking2 = org.mockito.Mockito.mock(Booking.class);
        bookings.add(mockedBooking1);
        bookings.add(mockedBooking2);

        guest.setBookingsHistory(bookings);

        assertEquals(2, guest.getBookingsHistory().size());
        assertTrue(guest.getBookingsHistory().contains(mockedBooking1));
        assertTrue(guest.getBookingsHistory().contains(mockedBooking2));
    }
}
