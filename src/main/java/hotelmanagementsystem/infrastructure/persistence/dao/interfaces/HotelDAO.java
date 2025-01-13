package hotelmanagementsystem.infrastructure.persistence.dao.interfaces;

import hotelmanagementsystem.infrastructure.persistence.entities.HotelEntity;
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
