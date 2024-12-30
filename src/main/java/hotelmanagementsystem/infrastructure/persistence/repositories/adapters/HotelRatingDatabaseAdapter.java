package hotelmanagementsystem.infrastructure.persistence.repositories.adapters;

import hotelmanagementsystem.domain.models.HotelRating;
import hotelmanagementsystem.infrastructure.persistence.dao.interfaces.HotelRatingDAO;
import hotelmanagementsystem.infrastructure.persistence.mapper.HotelRatingMapper;
import hotelmanagementsystem.infrastructure.persistence.repositories.interfaces.HotelRatingRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class HotelRatingDatabaseAdapter implements HotelRatingRepository {

    private final HotelRatingDAO hotelRatingDAO;
    private final HotelRatingMapper hotelRatingMapper;

    @Inject
    public HotelRatingDatabaseAdapter(HotelRatingDAO hotelRatingDAO, HotelRatingMapper hotelRatingMapper) {

        this.hotelRatingDAO = hotelRatingDAO;
        this.hotelRatingMapper = hotelRatingMapper;

    }

    @Override
    public HotelRating save(HotelRating rating) {
        return null;
    }

    @Override
    public HotelRating findById(Long id) {
        return null;
    }

    @Override
    public Map<Long, HotelRating> findByStarRating(String starRating) {
        return null;
    }

    @Override
    public void delete(HotelRating rating) {

    }

    @Override
    public List<HotelRating> findFilteredRatings(long hotelID, int starRating, boolean onlyWithComment) {
        return hotelRatingDAO.findFilteredRatings(hotelID, starRating, onlyWithComment)
                .map(hotelRatingMapper::mapToDomainList) // Map the list of entities to domain models
                .orElse(Collections.emptyList()); // Fallback: empty list
    }




}