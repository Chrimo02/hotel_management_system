package hotelmanagementsystem.infrastructure.persistence.repositories.interfaces;

import hotelmanagementsystem.domain.models.Hotel;

import java.util.Optional;
import java.util.List;

public interface HotelRepository {

    Optional<Hotel> findById(Long id);

    List<Hotel> findAll();

    void update(Hotel hotel);

    void deleteById(Long id);

    Hotel createHotel(Hotel hotel);
}
