package hotelmanagementsystem.infrastructure.persistence.repositories.interfaces;

import hotelmanagementsystem.domain.models.HotelLocation;

import java.util.List;

public interface HotelLocationRepository {

    void createHotelLocation(HotelLocation hotelLocation);

    void deleteHotelLocation(long id);


}
