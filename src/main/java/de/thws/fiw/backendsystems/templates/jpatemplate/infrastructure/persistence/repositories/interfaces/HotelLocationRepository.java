package de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.repositories.interfaces;

import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.HotelLocation;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.entities.HotelLocationEntity;

import java.util.List;
import java.util.Optional;

public interface HotelLocationRepository {

    void createHotelLocation(HotelLocation hotelLocation);
    List<HotelLocation> findAll();
    void updateAddress(long hotelId, String newAddress);
    void updateCountry(long hotelId, String newCountry);
    void updateCity(long hotelId, String newCity);
    void deleteHotelLocation(long id);
    <T> T getHotelLocationByHotelId(long hotelId, Class<T> returnType);


}
