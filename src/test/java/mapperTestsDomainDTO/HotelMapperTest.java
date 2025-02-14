package mapperTestsDomainDTO;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import hotelmanagementsystem.domain.models.Booking;
import hotelmanagementsystem.domain.models.Hotel;
import hotelmanagementsystem.domain.models.HotelRating;
import hotelmanagementsystem.domain.models.Room;
import hotelmanagementsystem.infrastructure.api.dto.HotelDTO;
import hotelmanagementsystem.infrastructure.api.dto.HotelRatingDTO;
import hotelmanagementsystem.infrastructure.api.mapper.HotelMapper;
import org.junit.jupiter.api.Test;

public class HotelMapperTest {

    @Test
    public void testToDTO_NullHotel() {
        HotelDTO dto = HotelMapper.toDTO(null);
        assertNull(dto);
    }

    @Test
    public void testToDTO_AllFields() {
        // Erzeuge Dummy-Hotel
        Hotel hotel = new Hotel.HotelBuilder()
                .withId(1L)
                .withName("Test Hotel")
                .withDescription("A great hotel")
                .withAverageRating(4.5)
                .withRoomsList(Collections.emptyList())
                .withBookingList(Collections.emptyList())
                .withRatingMap(Arrays.asList(
                        new HotelRating.Builder().withId(10L).withRating(5).withComment("Excellent").withGuest(null).build(),
                        new HotelRating.Builder().withId(11L).withRating(4).withComment("Good").withGuest(null).build()
                ))
                .build();

        HotelDTO dto = HotelMapper.toDTO(hotel);
        assertNotNull(dto);
        assertEquals(1L, dto.getId());
        assertEquals("Test Hotel", dto.getName());
        assertEquals("A great hotel", dto.getDescription());
        assertEquals(4.5, dto.getAverageRating(), 0.001);
        // Da Rooms und Bookings leer sind
        assertTrue(dto.getRoomIds().isEmpty());
        assertTrue(dto.getBookingIds().isEmpty());
        List<HotelRatingDTO> ratings = dto.getHotelRatings();
        assertEquals(2, ratings.size());
        assertEquals(5, ratings.get(0).getRating());
        assertEquals("Excellent", ratings.get(0).getComment());
        assertEquals(4, ratings.get(1).getRating());
        assertEquals("Good", ratings.get(1).getComment());
    }

    @Test
    public void testToDomain() {
        HotelDTO dto = new HotelDTO();
        dto.setId(2L);
        dto.setName("Sample Hotel");
        dto.setDescription("Sample Description");
        dto.setAverageRating(3.7);
        dto.setRoomIds(Arrays.asList(100L, 101L));
        dto.setBookingIds(Arrays.asList(200L, 201L));
        // Ratings hier setzen wir als leere Liste
        dto.setHotelRatings(Collections.emptyList());

        List<Room> rooms = Collections.emptyList();
        List<Booking> bookings = Collections.emptyList();

        Hotel hotel = HotelMapper.toDomain(dto, rooms, bookings);
        assertNotNull(hotel);
        assertEquals(2L, hotel.getId());
        assertEquals("Sample Hotel", hotel.getName());
        assertEquals("Sample Description", hotel.getDescription());
        assertEquals(3.7, hotel.getAverageRating(), 0.001);
        assertEquals(rooms, hotel.getRooms());
        assertEquals(bookings, hotel.getBookings());
    }
}
