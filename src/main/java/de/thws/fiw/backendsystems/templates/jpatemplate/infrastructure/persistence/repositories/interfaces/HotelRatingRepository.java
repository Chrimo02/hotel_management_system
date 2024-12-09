package de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.repositories.interfaces;

import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.HotelRatingEnum;

import java.util.List;

public interface HotelRatingRepository {
    HotelRatingEnum save(HotelRatingEnum rating);           // Save or update a rating
    HotelRatingEnum findById(Long id);                        // Find a rating by its ID
    List<HotelRatingEnum> findByStarRating(String starRating); // Find ratings by star rating
    void delete(HotelRatingEnum rating);
    List<HotelRatingEnum> findFilteredRatings(long hotelID, int starRating, boolean onlyWithComment);// Delete a rating
}

