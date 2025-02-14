package hotelmanagementsystem.infrastructure.persistence.dao.interfaces;

import hotelmanagementsystem.infrastructure.persistence.entities.HotelRatingEntity;

import java.util.List;


public interface HotelRatingDAO {
    HotelRatingEntity createRating(HotelRatingEntity rating);
    HotelRatingEntity findById(Long id);
    void deleteById(Long id);
    void updateRating(HotelRatingEntity rating);
    List<HotelRatingEntity> findFilteredRatings(long hotelID, int starRating, boolean onlyWithComment);
}