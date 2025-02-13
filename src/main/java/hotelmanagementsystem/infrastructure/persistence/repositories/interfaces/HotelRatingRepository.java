package hotelmanagementsystem.infrastructure.persistence.repositories.interfaces;

import hotelmanagementsystem.domain.models.HotelRating;

import java.util.List;
import java.util.Map;

public interface HotelRatingRepository {
    HotelRating save(HotelRating rating);
    HotelRating findById(Long id);
    //Map<Long, HotelRating> findByStarRating(String starRating);
    void delete(HotelRating rating);
    List<HotelRating> findFilteredRatings(long hotelID, int starRating, boolean onlyWithComment);
}

