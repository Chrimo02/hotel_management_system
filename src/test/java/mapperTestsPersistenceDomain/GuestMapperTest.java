package mapperTestsPersistenceDomain;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import hotelmanagementsystem.domain.models.Guest;
import hotelmanagementsystem.infrastructure.persistence.entities.GuestEntity;
import hotelmanagementsystem.infrastructure.persistence.mapper.GuestMapper;
import org.junit.jupiter.api.Test;

public class GuestMapperTest {

    // Wir verwenden hier eine einfache Instanz des GuestMapper – der Konstruktor benötigt ein BookingMapper, den wir
    // für diesen Test als null übergeben können, da er in unseren Methoden nicht verwendet wird.
    private GuestMapper guestMapper = new GuestMapper(null);

    @Test
    public void testGuestToGuestEntity() {
        Guest guest = new Guest.GuestBuilder()
                .withId(1L)
                .withFirstName("Alice")
                .withLastName("Smith")
                .withBirthday(1990, 5, 20)
                .withEMail("alice@example.com")
                .withPhoneNumber("1234567890")
                .build();

        GuestEntity entity = guestMapper.guestToGuestEntity(guest);
        assertNotNull(entity);
        assertEquals(1L, entity.getId());
        assertEquals("Alice", entity.getFirstName());
        assertEquals("Smith", entity.getLastName());
        assertEquals(LocalDate.of(1990, 5, 20), entity.getBirthday());
        assertEquals("alice@example.com", entity.geteMail());
        assertEquals("1234567890", entity.getPhoneNumber());
    }

    @Test
    public void testGuestEntityToGuest() {
        GuestEntity entity = new GuestEntity("Bob", "Brown", 1985, 3, 15, "bob@example.com", "9876543210");
        entity.setId(2L);

        Guest guest = guestMapper.guestEntityToGuest(entity);
        assertNotNull(guest);
        assertEquals(2L, guest.getId());
        assertEquals("Bob", guest.getFirstName());
        assertEquals("Brown", guest.getLastName());
        assertEquals(LocalDate.of(1985, 3, 15), guest.getBirthday());
        assertEquals("bob@example.com", guest.geteMail());
        assertEquals("9876543210", guest.getPhoneNumber());
    }

    @Test
    public void testGuestEntitiesToGuests() {
        GuestEntity entity1 = new GuestEntity("Alice", "Smith", 1990, 5, 20, "alice@example.com", "1234567890");
        entity1.setId(1L);
        GuestEntity entity2 = new GuestEntity("Bob", "Brown", 1985, 3, 15, "bob@example.com", "9876543210");
        entity2.setId(2L);

        List<Guest> guests = guestMapper.guestEntitiesToGuests(Arrays.asList(entity1, entity2));
        assertNotNull(guests);
        assertEquals(2, guests.size());
        assertEquals(1L, guests.get(0).getId());
        assertEquals(2L, guests.get(1).getId());
    }
}
