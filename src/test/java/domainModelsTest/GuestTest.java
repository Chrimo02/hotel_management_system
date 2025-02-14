package domainModelsTest;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import hotelmanagementsystem.domain.models.Guest;
import org.junit.jupiter.api.Test;

public class GuestTest {

    @Test
    public void testGuestBuilder_AllFieldsSet() {
        Guest guest = new Guest.GuestBuilder()
                .withId(1L)
                .withFirstName("John")
                .withLastName("Doe")
                .withBirthday(1990, 5, 20)
                .withEMail("john@example.com")
                .withPhoneNumber("1234567890")
                .build();

        assertEquals(1L, guest.getId(), "ID should be 1L");
        assertEquals("John", guest.getFirstName(), "FirstName should be 'John'");
        assertEquals("Doe", guest.getLastName(), "LastName should be 'Doe'");
        assertEquals(LocalDate.of(1990, 5, 20), guest.getBirthday(), "Birthday should be 1990-05-20");
        assertEquals("john@example.com", guest.geteMail(), "E-Mail should be 'john@example.com'");
        assertEquals("1234567890", guest.getPhoneNumber(), "PhoneNumber should be '1234567890'");
    }

    @Test
    public void testSetEmail() {
        Guest guest = new Guest.GuestBuilder()
                .withFirstName("Alice")
                .withEMail("alice@example.com")
                .build();
        assertEquals("alice@example.com", guest.geteMail());
        guest.seteMail("newalice@example.com");
        assertEquals("newalice@example.com", guest.geteMail());
    }

    @Test
    public void testSetPhoneNumber() {
        Guest guest = new Guest.GuestBuilder()
                .withFirstName("Bob")
                .withPhoneNumber("111222333")
                .build();
        assertEquals("111222333", guest.getPhoneNumber());
        guest.setPhoneNumber("444555666");
        assertEquals("444555666", guest.getPhoneNumber());
    }

    @Test
    public void testSetLastName() {
        Guest guest = new Guest.GuestBuilder()
                .withFirstName("Charlie")
                .withLastName("Smith")
                .build();
        assertEquals("Smith", guest.getLastName());
        guest.setLastName("Johnson");
        assertEquals("Johnson", guest.getLastName());
    }

    @Test
    public void testGuestBuilder_Minimal() {
        Guest guest = new Guest.GuestBuilder()
                .withFirstName("Minimal")
                .build();
        assertEquals("Minimal", guest.getFirstName());
        assertNull(guest.getLastName(), "LastName should be null");
        assertNull(guest.getBirthday(), "Birthday should be null");
        assertNull(guest.geteMail(), "E-Mail should be null");
        assertNull(guest.getPhoneNumber(), "PhoneNumber should be null");
    }
}


