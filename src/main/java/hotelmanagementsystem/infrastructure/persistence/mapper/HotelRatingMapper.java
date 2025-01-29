package hotelmanagementsystem.infrastructure.persistence.mapper;

import hotelmanagementsystem.domain.models.Booking;
import hotelmanagementsystem.domain.models.HotelRating;
import hotelmanagementsystem.infrastructure.persistence.entities.BookingEntity;
import hotelmanagementsystem.infrastructure.persistence.entities.HotelEntity;
import hotelmanagementsystem.infrastructure.persistence.entities.HotelRatingEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class HotelRatingMapper {

    private final BookingMapper bookingMapper;
    // private final HotelMapper hotelMapper; // Ggf. gar nicht mehr nötig

    @Inject
    public HotelRatingMapper(BookingMapper bookingMapper) {
        this.bookingMapper = bookingMapper;
    }

    // -----------------------------
    // ENTITY -> DOMAIN
    // -----------------------------
    public HotelRating mapToDomain(HotelRatingEntity entity) {
        if (entity == null) return null;

        // Booking minimal
        Booking bookingDomain = bookingMapper.bookingEntityToBooking(entity.getBooking());

        HotelRating ratingDomain = new HotelRating.Builder()
                .withId(entity.getId())
                .withRating(entity.getStarRating())
                .withComment(entity.getCommentRating())
                .withBooking(bookingDomain)
                // Guest -> Falls du willst, kannst du hier guestMapper holen
                // .withGuest(...)
                .build();
        return ratingDomain;
    }

    // -----------------------------
    // DOMAIN -> ENTITY
    // -----------------------------
    public HotelRatingEntity mapToEntity(HotelRating rating) {
        if (rating == null) return null;

        // Minimales BookingEntity
        BookingEntity bookingEntity = bookingMapper.bookingToBookingEntity(rating.getBooking());

        // Minimales HotelEntity? => Falls das RatingEntity ein Hotel braucht,
        // setze nur ID oder so. id = rating.getBooking().getHotel().getId()

        // Guest -> auch minimal per ID, etc.

        HotelRatingEntity entity = new HotelRatingEntity.Builder()
                .withId(rating.getId())
                .withRating(rating.getStarRating())
                .withComment(rating.getGuestComment())
                .withBooking(bookingEntity)
                .build();
        return entity;
    }

    // Helpers
    public List<HotelRating> mapToDomainList(List<HotelRatingEntity> list) {
        if (list == null) return Collections.emptyList();
        return list.stream()
                .map(this::mapToDomain)
                .collect(Collectors.toList());
    }

    public List<HotelRatingEntity> mapToEntityList(List<HotelRating> ratings) {
        if (ratings == null) return Collections.emptyList();
        return ratings.stream()
                .map(this::mapToEntity)
                .collect(Collectors.toList());
    }
}
