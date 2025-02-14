package domainModelsTest;

import static org.junit.jupiter.api.Assertions.*;

import hotelmanagementsystem.domain.models.Guest;
import hotelmanagementsystem.domain.models.Hotel;
import hotelmanagementsystem.domain.models.HotelLocation;
import hotelmanagementsystem.domain.models.HotelRating;
import org.junit.jupiter.api.Test;

public class HotelRatingTest {

    @Test
    public void testBuilder_AllFieldsSet() {
        // Erzeuge Dummy-Objekte für Guest und Hotel mithilfe der Builder (minimal)
        Guest dummyGuest = new Guest.GuestBuilder()
                .withId(1L)
                .withFirstName("Alice")
                .withLastName("Smith")
                .withBirthday(1990, 1, 1)
                .withEMail("alice@example.com")
                .withPhoneNumber("123456789")
                .build();

        Hotel dummyHotel = new Hotel.HotelBuilder()
                .withId(10L)
                .withName("Test Hotel")
                .withDescription("A nice hotel")
                .withLocation(new HotelLocation.HotelLocationBuilder()
                        .withId(1L)
                        .withAddress("123 Main St")
                        .withCity("TestCity")
                        .withCountry("TestCountry")
                        .build())
                .build();

        int ratingValue = 5;
        String comment = "Excellent service";
        Long expectedId = 100L;

        HotelRating rating = new HotelRating.Builder()
                .withId(expectedId)
                .withHotel(dummyHotel)
                .withGuest(dummyGuest)
                .withRating(ratingValue)
                .withComment(comment)
                .build();

        assertEquals(expectedId, rating.getId(), "ID should be set correctly");
        assertEquals(dummyHotel, rating.getHotel(), "Hotel should be set correctly");
        assertEquals(dummyGuest, rating.getGuest(), "Guest should be set correctly");
        assertEquals(ratingValue, rating.getStarRating(), "Rating should be set correctly");
        assertEquals(comment, rating.getGuestComment(), "Comment should be set correctly");
    }

    @Test
    public void testBuilder_DefaultComment() {
        // Erzeuge ein Rating ohne explizite Angabe eines Kommentars.
        HotelRating rating = new HotelRating.Builder()
                .withId(200L)
                .withRating(4)
                .build();

        // Da im Builder der Standardwert "" gesetzt wird, erwarten wir diesen.
        assertEquals("", rating.getGuestComment(), "Default comment should be empty string");
    }
}
