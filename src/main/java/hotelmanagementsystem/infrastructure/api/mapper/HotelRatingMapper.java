package hotelmanagementsystem.infrastructure.api.mapper;

import hotelmanagementsystem.domain.models.Guest;
import hotelmanagementsystem.domain.models.HotelRating;
import hotelmanagementsystem.infrastructure.api.dto.HotelRatingDTO;

public class HotelRatingMapper {


    public static HotelRatingDTO toDTO(HotelRating hotelRating) {
        if (hotelRating == null) {
            return null;
        }
        HotelRatingDTO dto = new HotelRatingDTO();
        dto.setId(hotelRating.getId() != null ? hotelRating.getId() : 0L);
        dto.setRating(hotelRating.getStarRating());
        dto.setComment(hotelRating.getGuestComment());
        dto.setGuestId(hotelRating.getGuest() != null ? hotelRating.getGuest().getId() : 0L);
        return dto;
    }


    public static HotelRating toDomain(HotelRatingDTO dto, Guest guest) {
        if (dto == null) {
            return null;
        }
        return new HotelRating.Builder()
                .withId(dto.getId())
                .withGuest(guest)
                .withRating(dto.getRating())
                .withComment(dto.getComment())
                .build();
    }
}
