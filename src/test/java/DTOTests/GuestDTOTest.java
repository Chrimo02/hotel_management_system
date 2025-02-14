package DTOTests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import hotelmanagementsystem.infrastructure.api.dto.GuestDTO;
import hotelmanagementsystem.infrastructure.api.grpc.generated.Guest;
import org.junit.jupiter.api.Test;

public class GuestDTOTest {

    @Test
    public void testGettersAndSetters() {
        GuestDTO dto = new GuestDTO();
        dto.setId(123L);
        dto.setFirstName("Alice");
        dto.setLastName("Smith");
        LocalDate birthday = LocalDate.of(1990, 1, 1);
        dto.setBirthday(birthday);
        dto.seteMail("alice@example.com");
        dto.setPhoneNumber("1234567890");

        assertEquals(123L, dto.getId());
        assertEquals("Alice", dto.getFirstName());
        assertEquals("Smith", dto.getLastName());
        assertEquals(birthday, dto.getBirthday());
        assertEquals("alice@example.com", dto.geteMail());
        assertEquals("1234567890", dto.getPhoneNumber());
    }

    @Test
    public void testToProtobuf() {
        GuestDTO dto = new GuestDTO();
        dto.setId(123L);
        dto.setFirstName("Alice");
        dto.setLastName("Smith");
        LocalDate birthday = LocalDate.of(1990, 1, 1);
        dto.setBirthday(birthday);
        dto.seteMail("alice@example.com");
        dto.setPhoneNumber("1234567890");

        Guest guestProto = dto.toProtobuf();
        assertEquals(123L, guestProto.getId());
        assertEquals("Alice", guestProto.getFirstName());
        assertEquals("Smith", guestProto.getLastName());
        assertEquals(birthday.toString(), guestProto.getBirthday());
        assertEquals("alice@example.com", guestProto.getEmail());
        assertEquals("1234567890", guestProto.getPhoneNumber());
    }
}
