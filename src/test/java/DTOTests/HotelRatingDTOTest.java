package DTOTests;

import static org.junit.jupiter.api.Assertions.*;

import hotelmanagementsystem.infrastructure.api.dto.HotelRatingDTO;
import hotelmanagementsystem.infrastructure.api.grpc.generated.HotelRating;
import org.junit.jupiter.api.Test;

public class HotelRatingDTOTest {

    @Test
    public void testGettersAndSetters() {
        HotelRatingDTO dto = new HotelRatingDTO();
        dto.setId(100L);
        dto.setRating(5);
        dto.setComment("Excellent");
        dto.setGuestId(200L);

        assertEquals(100L, dto.getId());
        assertEquals(5, dto.getRating());
        assertEquals("Excellent", dto.getComment());
        assertEquals(200L, dto.getGuestId());
    }

    @Test
    public void testToProtobuf() {
        HotelRatingDTO dto = new HotelRatingDTO();
        dto.setRating(4);
        dto.setComment("Good");
        dto.setGuestId(300L);

        HotelRating proto = dto.toProtobuf();
        assertEquals(4, proto.getStarRating());
        assertEquals("Good", proto.getComment());
    }
}
