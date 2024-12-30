package hotelmanagementsystem.infrastructure.persistence.repositories.interfaces;

import hotelmanagementsystem.domain.models.HotelLocation;

import java.util.List;

public interface HotelLocationRepository {

    void createHotelLocation(HotelLocation hotelLocation);
    List<HotelLocation> findAll();
    void updateAddress(long hotelId, String newAddress);
    void updateCountry(long hotelId, String newCountry);
    void updateCity(long hotelId, String newCity);
    void deleteHotelLocation(long id);
    <T> T getHotelLocationByHotelId(long hotelId, Class<T> returnType);


}
