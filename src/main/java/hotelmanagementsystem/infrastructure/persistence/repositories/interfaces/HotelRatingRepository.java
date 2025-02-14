package hotelmanagementsystem.infrastructure.persistence.repositories.interfaces;

import hotelmanagementsystem.domain.models.HotelRating;

import java.util.List;

public interface HotelRatingRepository {
    HotelRating save(HotelRating rating);
    HotelRating findById(Long id);
    void delete(HotelRating rating);
    List<HotelRating> findFilteredRatings(long hotelID, int starRating, boolean onlyWithComment);
}

