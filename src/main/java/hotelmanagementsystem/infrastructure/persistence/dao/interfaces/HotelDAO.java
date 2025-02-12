package hotelmanagementsystem.infrastructure.persistence.dao.interfaces;

import hotelmanagementsystem.infrastructure.persistence.entities.HotelEntity;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public interface HotelDAO {
    HotelEntity createHotel(HotelEntity hotel);
    Optional<HotelEntity> findById(Long id);
    HotelEntity updateHotel(HotelEntity hotel);
    void deleteById(Long id);

    //List<HotelEntity> findAll(); --> alt, war vor filtering

    long countByFilter(String city, double minRating);
    List<HotelEntity> findByFilter(String city, double minRating, int offset, int limit);

}
