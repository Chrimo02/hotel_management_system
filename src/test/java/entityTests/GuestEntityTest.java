package entityTests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import hotelmanagementsystem.infrastructure.persistence.entities.GuestEntity;
import org.junit.jupiter.api.Test;

public class GuestEntityTest {

    @Test
    public void testConstructorAndGetters() {
        GuestEntity guest = new GuestEntity("John", "Doe", 1990, 1, 1, "john@example.com", "123456789");
        assertEquals("John", guest.getFirstName());
        assertEquals("Doe", guest.getLastName());
        assertEquals(LocalDate.of(1990, 1, 1), guest.getBirthday());
        assertEquals("john@example.com", guest.geteMail());
        assertEquals("123456789", guest.getPhoneNumber());
    }

    @Test
    public void testSetters() {
        GuestEntity guest = new GuestEntity();
        guest.setId(10L);
        guest.setFirstName("Alice");
        guest.setLastName("Smith");
        guest.setBirthday(LocalDate.of(1985, 5, 20));
        guest.seteMail("alice@example.com");
        guest.setPhoneNumber("987654321");

        assertEquals(10L, guest.getId());
        assertEquals("Alice", guest.getFirstName());
        assertEquals("Smith", guest.getLastName());
        assertEquals(LocalDate.of(1985, 5, 20), guest.getBirthday());
        assertEquals("alice@example.com", guest.geteMail());
        assertEquals("987654321", guest.getPhoneNumber());
    }
}
