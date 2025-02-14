package entityTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import hotelmanagementsystem.infrastructure.persistence.entities.*;
import org.junit.jupiter.api.Test;

public class HotelEntityTest {

    @Test
    public void testHotelBuilderAndGetters() {
        HotelLocationEntity location = new HotelLocationEntity.HotelLocationBuilder()
                .withAddress("123 Main St")
                .withCity("TestCity")
                .withCountry("TestCountry")
                .build();
        List<RoomEntity> rooms = Arrays.asList();
        List<BookingEntity> bookings = Arrays.asList();
        List<HotelRatingEntity> ratings = Arrays.asList();

        HotelEntity hotel = new HotelEntity.HotelBuilder()
                .withId(1L)
                .withName("Test Hotel")
                .withDescription("Test Description")
                .withLocation(location)
                .withRooms(rooms)
                .withBookings(bookings)
                .withRatings(ratings)
                .withAverageRating(4.0)
                .build();

        assertEquals(1L, hotel.getId());
        assertEquals("Test Hotel", hotel.getName());
        assertEquals("Test Description", hotel.getDescription());
        assertEquals(location, hotel.getLocation());
        assertEquals(4.0, hotel.getAverageRating(), 0.001);
        assertEquals(rooms, hotel.getRooms());
        assertEquals(bookings, hotel.getBookings());
        assertEquals(ratings, hotel.getRatings());
    }
}
