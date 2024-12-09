package de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.dao.interfaces;

import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.HotelLocation;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.entities.HotelLocationEntity;

import java.util.List;

public interface HotelLocationDAO {
    void create(HotelLocationEntity hotelLocation);
    HotelLocationEntity read(long id);
    List<HotelLocationEntity> readAll();
    void update(HotelLocationEntity hotelLocation);
    void delete(HotelLocationEntity hotelLocation);
    HotelLocationEntity findLocationByHotelId(long hotelId);
}
