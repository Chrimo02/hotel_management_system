package hotelmanagementsystem.infrastructure.persistence.dao.interfaces;

import hotelmanagementsystem.infrastructure.persistence.entities.HotelRatingEntity;

import java.util.List;
import java.util.Optional;
import java.util.Map;


public interface HotelRatingDAO {
    HotelRatingEntity createRating(HotelRatingEntity rating);
    HotelRatingEntity findById(Long id);
    Map<Long, HotelRatingEntity> findAll();
    void deleteById(Long id);
    void updateRating(HotelRatingEntity rating);
    List<HotelRatingEntity> findFilteredRatings(long hotelID, int starRating, boolean onlyWithComment);
}