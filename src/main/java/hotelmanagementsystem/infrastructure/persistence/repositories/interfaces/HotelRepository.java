package hotelmanagementsystem.infrastructure.persistence.repositories.interfaces;

import hotelmanagementsystem.domain.models.Hotel;
import hotelmanagementsystem.domain.models.PagedHotels;

import java.util.Optional;
import java.util.List;

public interface HotelRepository {

    Hotel findById(Long id);

    PagedHotels findPagedByFilter(String city, double minRating, int pageNumber, int pageSize);

    Hotel update(Hotel hotel);

    void deleteById(Long id);

    Hotel createHotel(Hotel hotel);
}
