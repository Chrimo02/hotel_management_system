package hotelmanagementsystem.infrastructure.persistence.repositories.adapters;

import hotelmanagementsystem.domain.models.HotelRating;
import hotelmanagementsystem.infrastructure.persistence.dao.interfaces.HotelRatingDAO;
import hotelmanagementsystem.infrastructure.persistence.entities.HotelRatingEntity;
import hotelmanagementsystem.infrastructure.persistence.mapper.HotelRatingMapper;
import hotelmanagementsystem.infrastructure.persistence.repositories.interfaces.HotelRatingRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class HotelRatingDatabaseAdapter implements HotelRatingRepository {

    private final HotelRatingDAO hotelRatingDAO;
    private final HotelRatingMapper hotelRatingMapper;

    @Inject
    public HotelRatingDatabaseAdapter(HotelRatingDAO hotelRatingDAO,
                                      HotelRatingMapper hotelRatingMapper) {
        this.hotelRatingDAO = hotelRatingDAO;
        this.hotelRatingMapper = hotelRatingMapper;
    }

    @Override
    public HotelRating save(HotelRating rating) {
        try {
            HotelRatingEntity ratingEntity = hotelRatingMapper.mapToEntity(rating);
            if (rating.getId() == null) {
                HotelRatingEntity optCreated = hotelRatingDAO.createRating(ratingEntity);
                return hotelRatingMapper.mapToDomain(optCreated);
            } else {
                hotelRatingDAO.updateRating(ratingEntity);
                HotelRatingEntity updatedEntity = hotelRatingDAO.findById(rating.getId());
                return hotelRatingMapper.mapToDomain(updatedEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error saving Hotel Rating - DB ADAPTER", e);
        }
    }

    @Override
    public HotelRating findById(Long id) {
        try {
            HotelRatingEntity optionalEntity = hotelRatingDAO.findById(id);
            return hotelRatingMapper.mapToDomain(optionalEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error finding Hotel Rating by id - DB ADAPTER", e);
        }
    }

    @Override
    public void delete(HotelRating rating) {
        try {
            if (rating.getId() == null) return;
            hotelRatingDAO.deleteById(rating.getId());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error deleting Hotel Rating - DB ADAPTER", e);
        }
    }

    @Override
    public List<HotelRating> findFilteredRatings(long hotelID, int starRating, boolean onlyWithComment) {
        try {
            return hotelRatingMapper.mapToDomainList(hotelRatingDAO.findFilteredRatings(hotelID, starRating, onlyWithComment));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error finding filtered Hotel Ratings - DB ADAPTER", e);
        }
    }


}
