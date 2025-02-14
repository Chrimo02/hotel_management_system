package mapperTestsPersistenceDomain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Collections;

import hotelmanagementsystem.domain.models.HotelRating;
import hotelmanagementsystem.domain.models.Guest;
import hotelmanagementsystem.infrastructure.persistence.entities.GuestEntity;
import hotelmanagementsystem.infrastructure.persistence.entities.HotelRatingEntity;
import hotelmanagementsystem.infrastructure.persistence.mapper.GuestMapper;
import hotelmanagementsystem.infrastructure.persistence.mapper.HotelRatingMapper;
import org.junit.jupiter.api.Test;

public class HotelRatingMapperTest {

    // Für diesen Test verwenden wir eine echte Instanz von GuestMapper,
    // da sie einfache Feldkopien durchführt.
    private GuestMapper guestMapper = new GuestMapper(null);
    private HotelRatingMapper mapper = new HotelRatingMapper(guestMapper);

    @Test
    public void testMapToDomain() {
        // Erstelle einen Dummy GuestEntity
        GuestEntity guestEntity = new GuestEntity("John", "Doe", 1990, 1, 1, "john@example.com", "123456789");
        guestEntity.setId(20L);
        HotelRatingEntity entity = new HotelRatingEntity.Builder()
                .withId(1L)
                .withRating(5)
                .withComment("Excellent")
                .withGuest(guestEntity)
                .build();

        HotelRating rating = mapper.mapToDomain(entity);
        assertNotNull(rating);
        assertEquals(entity.getId(), rating.getId());
        assertEquals(entity.getStarRating(), rating.getStarRating());
        assertEquals(entity.getCommentRating(), rating.getGuestComment());
        // Da in mapToDomain() das Hotel-Objekt nicht gemappt wird, erwarten wir null
        assertNull(rating.getHotel());
    }

    @Test
    public void testMapToEntity() {
        // Erstelle einen Dummy Guest
        Guest dummyGuest = new Guest.GuestBuilder()
                .withId(20L)
                .withFirstName("John")
                .withLastName("Doe")
                .withBirthday(1990, 1, 1)
                .withEMail("john@example.com")
                .withPhoneNumber("123456789")
                .build();
        HotelRating rating = new HotelRating.Builder()
                .withId(1L)
                .withRating(4)
                .withComment("Good")
                .withGuest(dummyGuest)
                .build();

        HotelRatingEntity entity = mapper.mapToEntity(rating);
        assertNotNull(entity);
        assertEquals(rating.getId(), entity.getId());
        assertEquals(rating.getStarRating(), entity.getStarRating());
        assertEquals(rating.getGuestComment(), entity.getCommentRating());
        // Da Hotel wird nicht gemappt, bleibt null
        assertNull(entity.getHotelId());
    }

    @Test
    public void testMapToDomainListAndEntityList() {
        // Erstelle eine Liste von HotelRating-Objekten
        Guest dummyGuest = new Guest.GuestBuilder()
                .withId(20L)
                .withFirstName("John")
                .withLastName("Doe")
                .withBirthday(1990, 1, 1)
                .withEMail("john@example.com")
                .withPhoneNumber("123456789")
                .build();
        HotelRating rating1 = new HotelRating.Builder()
                .withId(1L)
                .withRating(5)
                .withComment("Excellent")
                .withGuest(dummyGuest)
                .build();
        HotelRating rating2 = new HotelRating.Builder()
                .withId(2L)
                .withRating(4)
                .withComment("Good")
                .withGuest(dummyGuest)
                .build();

        List<HotelRating> ratings = Arrays.asList(rating1, rating2);
        List<HotelRatingEntity> entities = mapper.mapToEntityList(ratings);
        assertEquals(2, entities.size());

        List<HotelRating> mappedBack = mapper.mapToDomainList(entities);
        assertEquals(2, mappedBack.size());
        assertEquals(5, mappedBack.get(0).getStarRating());
        assertEquals(4, mappedBack.get(1).getStarRating());
    }
}
