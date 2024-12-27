package de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.dao.interfaces;

import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.entities.HotelRatingEntity;
import java.util.List;
import java.util.Optional;
import java.util.Map;


public interface HotelRatingDAO {
    Optional<HotelRatingEntity> createRating(HotelRatingEntity rating);           // Save or update a rating
    Optional<HotelRatingEntity> findById(Long id);                        // Find a rating by its ID
    Optional<Map<Long, HotelRatingEntity>> findAll(); // Find ratings by star rating
    void deleteById(Long id);
    void updateRating(HotelRatingEntity rating);
    Optional<List<HotelRatingEntity>> findFilteredRatings(long hotelID, int starRating, boolean onlyWithComment);// Delete a rating
}