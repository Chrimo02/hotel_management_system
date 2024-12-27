package de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.dao.interfaces;

import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.Hotel;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.entities.HotelEntity;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public interface HotelDAO {
    EntityManager entityManager();
    HotelEntity createHotel(HotelEntity hotel);
    Optional<HotelEntity> findById(Long id);
    List<HotelEntity> findAll();
    void updateHotel(HotelEntity hotel);
    void deleteById(Long id);
}
