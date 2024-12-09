package de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.repositories.adapters;

import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.HotelRating;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.dao.interfaces.HotelRatingDAO;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.repositories.interfaces.HotelRatingRepository;

import java.util.List;

public class HotelRatingDatabaseAdapter implements HotelRatingRepository {

    private final HotelRatingDAO hotelRatingDAO;

    @Inject
    public HotelRatingDatabaseAdapter(HotelRatingDAO hotelRatingDAO) {
        this.hotelRatingDAO = hotelRatingDAO;
    }

    @Override
    public HotelRatingEnum save(HotelRatingEnum rating) {
        return null;
    }

    @Override
    public HotelRatingEnum findById(Long id) {
        return null;
    }

    @Override
    public List<HotelRatingEnum> findByStarRating(String starRating) {
        return null;
    }

    @Override
    public void delete(HotelRatingEnum rating) {

    }

    @Override
    public List<HotelRating> findFilteredRatings(long hotelID, int starRating, boolean onlyWithComment) {
        return hotelRatingDAO.findFilteredRatings(hotelID, starRating, onlyWithComment)
                .map(this::mapToDomainList) // Mappen der Liste von Entity auf Domain
                .orElse(Collections.emptyList()); // Fallback: leere Liste
    }

    private List<HotelRating> mapToDomainList(List<HotelRatingEntity> hotelRatingEntities) {
        return hotelRatingEntities.stream()
                .map(this::mapToDomain)// Jedes Entity mappen
                .collect(Collectors.toList());// Als Liste zurückgeben
    }

    private HotelRating mapToDomain(HotelRatingEntity entity) {
        // Erstellen der Domain-Instanz basierend auf der Entity
        HotelRating hotelRating = new HotelRating.Builder(entity.getStarRating());
        return hotelRating;
    }

}