package hotelmanagementsystem.infrastructure.persistence.repositories.interfaces;

import hotelmanagementsystem.domain.models.HotelRating;

import java.util.List;
import java.util.Map;

public interface HotelRatingRepository {
    HotelRating save(HotelRating rating);           // Save or update a rating
    HotelRating findById(Long id);                        // Find a rating by its ID
    Map<Long, HotelRating> findByStarRating(String starRating); // Find ratings by star rating
    void delete(HotelRating rating);
    List<HotelRating> findFilteredRatings(long hotelID, int starRating, boolean onlyWithComment);// Delete a rating
}

