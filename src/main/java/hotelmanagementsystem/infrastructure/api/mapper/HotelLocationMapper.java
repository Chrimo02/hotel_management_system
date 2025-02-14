package hotelmanagementsystem.infrastructure.api.mapper;

import hotelmanagementsystem.domain.models.HotelLocation;
import hotelmanagementsystem.infrastructure.api.dto.HotelLocationDTO;

public class HotelLocationMapper {

    public static HotelLocationDTO toDTO(HotelLocation domainObject) {
        if (domainObject == null) {
            return null;
        }
        HotelLocationDTO dto = new HotelLocationDTO();
        dto.setAddress(domainObject.getAddress());
        dto.setCity(domainObject.getCity());
        dto.setCountry(domainObject.getCountry());
        return dto;
    }

    public static HotelLocation fromDTO(HotelLocationDTO dto) {
        if (dto == null) {
            return null;
        }
        return new HotelLocation.HotelLocationBuilder()
                .withAddress(dto.getAddress())
                .withCity(dto.getCity())
                .withCountry(dto.getCountry())
                .build();
    }
}