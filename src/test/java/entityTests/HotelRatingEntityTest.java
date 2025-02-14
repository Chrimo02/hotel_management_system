package entityTests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import hotelmanagementsystem.infrastructure.persistence.entities.GuestEntity;
import hotelmanagementsystem.infrastructure.persistence.entities.HotelRatingEntity;
import org.junit.jupiter.api.Test;

public class HotelRatingEntityTest {

    @Test
    public void testHotelRatingBuilderAndGetters() {
        // Erstelle einen Dummy GuestEntity
        GuestEntity guest = new GuestEntity("John", "Doe", 1990, 1, 1, "john@example.com", "123456789");
        HotelRatingEntity rating = new HotelRatingEntity.Builder()
                .withId(1L)
                .withRating(5)
                .withComment("Excellent")
                .withGuest(guest)
                .build();

        assertEquals(1L, rating.getId());
        assertEquals(5, rating.getStarRating());
        assertEquals("Excellent", rating.getCommentRating());
        assertEquals(guest, rating.getGuest());
    }
}
