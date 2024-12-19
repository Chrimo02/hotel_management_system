package de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.repositories.adapters;

import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.HotelRating;
import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.HotelRatingEnum;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.dao.interfaces.HotelRatingDAO;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.entities.HotelRatingEntity;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.mapper.HotelRatingMapper;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.repositories.interfaces.HotelRatingRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public Map<Long, HotelRating> findFilteredRatings(long hotelID, int starRating, boolean onlyWithComment) {
        return hotelRatingDAO.findFilteredRatings(hotelID, starRating, onlyWithComment)
                .map(hotelRatingMapper::mapToDomainMap) // Mappen der Liste von Entity auf Domain
                .orElse(Collections.emptyMap()); // Fallback: leere Liste
    }



}