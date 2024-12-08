package de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.repositories.interfaces;

import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.HotelRating;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.entities.HotelRatingEntity;
import java.util.List;

public interface HotelRatingRepository {
    HotelRating save(HotelRating rating);           // Save or update a rating
    HotelRating findById(Long id);                        // Find a rating by its ID
    List<HotelRating> findByStarRating(String starRating); // Find ratings by star rating
    void delete(HotelRating rating);
    List<HotelRating> findFilteredRatings(long hotelID, int starRating, boolean onlyWithComment);// Delete a rating
}

