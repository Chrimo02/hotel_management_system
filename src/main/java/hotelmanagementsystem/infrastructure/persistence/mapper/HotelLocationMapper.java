package hotelmanagementsystem.infrastructure.persistence.mapper;

import hotelmanagementsystem.domain.models.HotelLocation;
import hotelmanagementsystem.infrastructure.persistence.entities.HotelLocationEntity;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HotelLocationMapper {

    public HotelLocationEntity mapToEntity(HotelLocation hotelLocation) {
        return new HotelLocationEntity.HotelLocationBuilder()
                .withCity(hotelLocation.getCity())
                .withAddress(hotelLocation.getAddress())
                .withCountry(hotelLocation.getCountry())
                .build();
    }

    public HotelLocation mapToDomain(HotelLocationEntity hotelLocationEntity) {
        return new HotelLocation.HotelLocationBuilder()
                .withId(hotelLocationEntity.getId())
                .withCity(hotelLocationEntity.getCity())
                .withAddress(hotelLocationEntity.getAddress())
                .withCountry(hotelLocationEntity.getCountry())
                .build();
    }
}
