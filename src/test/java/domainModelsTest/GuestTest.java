package domainModelsTest;


import hotelmanagementsystem.domain.models.Booking;
import hotelmanagementsystem.domain.models.Guest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

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
        assertEquals(LocalDate.of(1990, 5, 20), guest.getBirthday());
        assertEquals("john.doe@example.com", guest.geteMail());
        assertEquals("1234567890", guest.getPhoneNumber());
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
    public void testGuestBuilder_WithOptionalFieldsAndMockedBookings() {
        Guest guestWithBookings = new Guest.GuestBuilder()
                .withId(2L)
                .withFirstName("Jane")
                .build();
        assertEquals(2L, guestWithBookings.getId());
        assertEquals("Jane", guestWithBookings.getFirstName());
    }

    @Test
    public void testBuilder_WhenCreatingMinimalGuest_OptionalFieldsRemainNull() {
        Guest minimalGuest = new Guest.GuestBuilder()
                .withFirstName("Minimal")
                .build();

        assertEquals("Minimal", minimalGuest.getFirstName());
        assertNull(minimalGuest.getLastName());
        assertNull(minimalGuest.getBirthday());
        assertNull(minimalGuest.geteMail());
        assertNull(minimalGuest.getPhoneNumber());
    }
}
