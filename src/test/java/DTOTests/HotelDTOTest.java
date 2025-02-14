package DTOTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import hotelmanagementsystem.infrastructure.api.dto.HotelDTO;
import hotelmanagementsystem.infrastructure.api.dto.HotelRatingDTO;
import hotelmanagementsystem.infrastructure.api.grpc.generated.Hotel;
import org.junit.jupiter.api.Test;

public class HotelDTOTest {

    @Test
    public void testGettersAndSetters() {
        HotelDTO dto = new HotelDTO();
        dto.setId(1L);
        dto.setName("Test Hotel");
        dto.setDescription("A great hotel");
        dto.setAverageRating(4.5);
        List<Long> roomIds = Arrays.asList(10L, 20L);
        List<Long> bookingIds = Arrays.asList(30L, 40L);
        dto.setRoomIds(roomIds);
        dto.setBookingIds(bookingIds);
        List<HotelRatingDTO> ratings = Collections.singletonList(
                createDummyHotelRatingDTO(5, "Excellent", 50L)
        );
        dto.setHotelRatings(ratings);

        assertEquals(1L, dto.getId());
        assertEquals("Test Hotel", dto.getName());
        assertEquals("A great hotel", dto.getDescription());
        assertEquals(4.5, dto.getAverageRating(), 0.001);
        assertEquals(roomIds, dto.getRoomIds());
        assertEquals(bookingIds, dto.getBookingIds());
        assertEquals(ratings, dto.getHotelRatings());
    }

    @Test
    public void testToProtobuf() {
        HotelDTO dto = new HotelDTO();
        dto.setId(2L);
        dto.setName("Sample Hotel");
        dto.setDescription("Sample Description");
        dto.setAverageRating(3.7);
        List<Long> roomIds = Arrays.asList(100L, 200L);
        List<Long> bookingIds = Arrays.asList(300L, 400L);
        dto.setRoomIds(roomIds);
        dto.setBookingIds(bookingIds);
        List<HotelRatingDTO> ratings = Arrays.asList(
                createDummyHotelRatingDTO(5, "Excellent", 500L),
                createDummyHotelRatingDTO(4, "Good", 600L)
        );
        dto.setHotelRatings(ratings);

        Hotel proto = dto.toProtobuf();
        assertEquals(2L, proto.getId());
        assertEquals("Sample Hotel", proto.getName());
        assertEquals("Sample Description", proto.getDescription());
        assertEquals(3.7, proto.getAverageRating(), 0.001);
        assertEquals(roomIds, proto.getRoomIdsList());
        assertEquals(bookingIds, proto.getBookingIdsList());
        // Hier wird der Mapper für Ratings aufgerufen:
        List<hotelmanagementsystem.infrastructure.api.grpc.generated.HotelRating> protoRatings = proto.getHotelRatingsList();
        assertEquals(ratings.size(), protoRatings.size());
        for (int i = 0; i < ratings.size(); i++) {
            // Vergleiche hier beispielsweise nur die starRating und comment (wie in HotelRatingDTO.toProtobuf())
            assertEquals(ratings.get(i).getRating(), protoRatings.get(i).getStarRating());
            assertEquals(ratings.get(i).getComment(), protoRatings.get(i).getComment());
        }
    }

    private HotelRatingDTO createDummyHotelRatingDTO(int rating, String comment, long guestId) {
        HotelRatingDTO dto = new HotelRatingDTO();
        dto.setRating(rating);
        dto.setComment(comment);
        dto.setGuestId(guestId);
        return dto;
    }
}
