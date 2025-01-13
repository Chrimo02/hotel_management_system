package hotelmanagementsystem.infrastructure.persistence.dao.interfaces;

import hotelmanagementsystem.infrastructure.persistence.entities.HotelLocationEntity;

import java.util.List;

public interface HotelLocationDAO {
    void create(HotelLocationEntity hotelLocation);
    HotelLocationEntity read(long id);
    List<HotelLocationEntity> readAll();
    void update(HotelLocationEntity hotelLocation);
    void delete(HotelLocationEntity hotelLocation);
    HotelLocationEntity findLocationByHotelId(long hotelId);
}
