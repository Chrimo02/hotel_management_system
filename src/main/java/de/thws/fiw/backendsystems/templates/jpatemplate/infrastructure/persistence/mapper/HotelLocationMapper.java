package de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.mapper;

import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.HotelLocation;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.entities.HotelLocationEntity;
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
        return new HotelLocation.HotelLocationBuilder(hotelLocationEntity.getId())
                .withCity(hotelLocationEntity.getCity())
                .withAddress(hotelLocationEntity.getAddress())
                .withCountry(hotelLocationEntity.getCountry())
                .build();
    }
}
