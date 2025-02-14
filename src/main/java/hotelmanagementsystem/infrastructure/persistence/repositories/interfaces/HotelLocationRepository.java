package hotelmanagementsystem.infrastructure.persistence.repositories.interfaces;

import hotelmanagementsystem.domain.models.HotelLocation;

public interface HotelLocationRepository {

    void createHotelLocation(HotelLocation hotelLocation);

    void deleteHotelLocation(long id);


}
