package de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.repositories.adapters;

import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.HotelRating;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.dao.interfaces.HotelRatingDAO;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.repositories.interfaces.HotelRatingRepository;

import java.util.List;

public class HotelRatingDatabaseAdapter implements HotelRatingRepository {

    private final HotelRatingDAO hotelRatingDAO;

    public HotelRatingDatabaseAdapter(HotelRatingDAO hotelRatingDAO) {
        this.hotelRatingDAO = hotelRatingDAO;
    }

    @Override
    public List<HotelRating> findFilteredRatings(long hotelID, int starRating, boolean onlyWithComment) {
        return hotelRatingDAO.findFilteredRatings(hotelID, starRating, onlyWithComment);
    }
}
