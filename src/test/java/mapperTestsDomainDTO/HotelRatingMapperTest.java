package mapperTestsDomainDTO;

import static org.junit.jupiter.api.Assertions.*;

import hotelmanagementsystem.domain.models.Guest;
import hotelmanagementsystem.domain.models.HotelRating;
import hotelmanagementsystem.infrastructure.api.dto.HotelRatingDTO;
import hotelmanagementsystem.infrastructure.api.mapper.HotelRatingMapper;
import org.junit.jupiter.api.Test;

public class HotelRatingMapperTest {

    @Test
    public void testToDTO_NullInput() {
        HotelRatingDTO dto = HotelRatingMapper.toDTO(null);
        assertNull(dto);
    }

    @Test
    public void testToDTO_AllFields() {
        Guest dummyGuest = new Guest.GuestBuilder()
                .withId(50L)
                .withFirstName("John")
                .withLastName("Doe")
                .withBirthday(1990, 1, 1)
                .withEMail("john@example.com")
                .withPhoneNumber("123456789")
                .build();
        HotelRating rating = new HotelRating.Builder()
                .withId(10L)
                .withRating(5)
                .withComment("Excellent")
                .withGuest(dummyGuest)
                .build();
        HotelRatingDTO dto = HotelRatingMapper.toDTO(rating);
        assertNotNull(dto);
        assertEquals(10L, dto.getId());
        assertEquals(5, dto.getRating());
        assertEquals("Excellent", dto.getComment());
        assertEquals(50L, dto.getGuestId());
    }

    @Test
    public void testToDomain() {
        HotelRatingDTO dto = new HotelRatingDTO();
        dto.setId(11L);
        dto.setRating(4);
        dto.setComment("Good");
        dto.setGuestId(60L);

        Guest dummyGuest = new Guest.GuestBuilder()
                .withId(60L)
                .withFirstName("Alice")
                .withLastName("Smith")
                .withBirthday(1985, 6, 15)
                .withEMail("alice@example.com")
                .withPhoneNumber("987654321")
                .build();

        HotelRating rating = HotelRatingMapper.toDomain(dto, dummyGuest);
        assertNotNull(rating);
        assertEquals(11L, rating.getId());
        assertEquals(4, rating.getStarRating());
        assertEquals("Good", rating.getGuestComment());
        assertNull(rating.getHotel());
        assertEquals(dummyGuest, rating.getGuest());
    }
}
