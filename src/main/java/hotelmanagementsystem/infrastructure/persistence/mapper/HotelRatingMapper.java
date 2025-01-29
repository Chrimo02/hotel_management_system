package hotelmanagementsystem.infrastructure.persistence.mapper;

import hotelmanagementsystem.domain.models.HotelRating;
import hotelmanagementsystem.infrastructure.persistence.entities.HotelRatingEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class HotelRatingMapper {

    private final BookingMapper bookingMapper;
    private final HotelMapper hotelMapper;

    @Inject
    public HotelRatingMapper(BookingMapper bookingMapper, HotelMapper hotelMapper) {
        this.bookingMapper = bookingMapper;
        this.hotelMapper = hotelMapper;
    }

    // Map a list of HotelRatingEntity to a list of HotelRating
    public List<HotelRating> mapToDomainList(List<HotelRatingEntity> hotelRatingEntities) {
        return hotelRatingEntities.stream()
                .map(this::mapToDomain) // Map each entity to its domain representation
                .collect(Collectors.toList());
    }

    // Map a list of HotelRating to a list of HotelRatingEntity
    public List<HotelRatingEntity> mapToEntityList(List<HotelRating> hotelRatings) {
        if (hotelRatings == null) {
            return Collections.emptyList();
        }
        return hotelRatings.stream()
                .map(this::mapToEntity) // Map each domain model to its entity representation
                .collect(Collectors.toList());
    }

    // Map a single HotelRatingEntity to its domain model
    public HotelRating mapToDomain(HotelRatingEntity entity) {
        return new HotelRating.Builder()
                .withRating(entity.getStarRating())
                .withId(entity.getId())
                .withComment(entity.getCommentRating())
                .withBooking(bookingMapper.bookingEntityToBooking(entity.getBooking()))
                .build();
    }

    // Map a single HotelRating to its entity representation
    public HotelRatingEntity mapToEntity(HotelRating rating) {
        return new HotelRatingEntity.Builder()
                .withRating(rating.getStarRating())
                .withBooking(bookingMapper.bookingToBookingEntity(rating.getBooking()))
                .withId(rating.getId())
                .withComment(rating.getGuestComment())
                .withHotel(hotelMapper.mapDomainHotelToHotelEntity(rating.getBooking().getHotel()))
                .build();
    }
}
