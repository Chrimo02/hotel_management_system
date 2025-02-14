package hotelmanagementsystem.infrastructure.api.mapper;

import hotelmanagementsystem.domain.models.HotelLocation;
import hotelmanagementsystem.infrastructure.api.dto.HotelLocationDTO;

public class HotelLocationMapper {

    // Domain-Model → DTO
    public static HotelLocationDTO toDTO(HotelLocation domainObject) {
        HotelLocationDTO dto = new HotelLocationDTO();
        dto.setId(domainObject.getId());
        dto.setAddress(domainObject.getAddress());
        dto.setCity(domainObject.getCity());
        dto.setCountry(domainObject.getCountry());
        return dto;
    }

    // DTO → Domain-Model (unter Verwendung des Builders)
    public static HotelLocation fromDTO(HotelLocationDTO dto) {
        return new HotelLocation.HotelLocationBuilder()
                .withId(dto.getId())
                .withAddress(dto.getAddress())
                .withCity(dto.getCity())
                .withCountry(dto.getCountry())
                .build();
    }
}