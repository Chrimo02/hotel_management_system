package mapperTestsDomainDTO;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import hotelmanagementsystem.domain.models.Guest;
import hotelmanagementsystem.infrastructure.api.dto.GuestDTO;
import hotelmanagementsystem.infrastructure.api.mapper.GuestMapper;
import org.junit.jupiter.api.Test;

public class GuestMapperTest {

    @Test
    public void testToDTO() {
        Guest guest = new Guest.GuestBuilder()
                .withId(1L)
                .withFirstName("Alice")
                .withLastName("Smith")
                .withBirthday(1990, 1, 1)
                .withEMail("alice@example.com")
                .withPhoneNumber("123456789")
                .build();
        GuestDTO dto = GuestMapper.toDTO(guest);
        assertEquals(1L, dto.getId());
        assertEquals("Alice", dto.getFirstName());
        assertEquals("Smith", dto.getLastName());
        assertEquals(LocalDate.of(1990, 1, 1), dto.getBirthday());
        assertEquals("alice@example.com", dto.geteMail());
        assertEquals("123456789", dto.getPhoneNumber());
    }

    @Test
    public void testToDomain() {
        GuestDTO dto = new GuestDTO();
        dto.setId(2L);
        dto.setFirstName("Bob");
        dto.setLastName("Brown");
        dto.setBirthday(LocalDate.of(1985, 5, 15));
        dto.seteMail("bob@example.com");
        dto.setPhoneNumber("987654321");

        Guest guest = GuestMapper.toDomain(dto);
        assertEquals(2L, guest.getId());
        assertEquals("Bob", guest.getFirstName());
        assertEquals("Brown", guest.getLastName());
        assertEquals(LocalDate.of(1985, 5, 15), guest.getBirthday());
        assertEquals("bob@example.com", guest.geteMail());
        assertEquals("987654321", guest.getPhoneNumber());
    }
}
