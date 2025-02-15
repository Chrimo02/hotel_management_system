package hotelmanagementsystem.infrastructure.api.mapper;

import hotelmanagementsystem.domain.models.RoomIdentifier;
import hotelmanagementsystem.infrastructure.api.dto.RoomIdentifierDTO;

public class RoomIdentifierMapper {

    public static RoomIdentifierDTO toDTO(RoomIdentifier roomIdentifier) {
        if (roomIdentifier == null) {
            return null;
        }
        return new RoomIdentifierDTO(
                roomIdentifier.getBuilding(),
                roomIdentifier.getFloor(),
                roomIdentifier.getRoomNumber()
        );
    }

    public static RoomIdentifier toDomain(RoomIdentifierDTO dto) {
        if (dto == null) {
            return null;
        }
        return new RoomIdentifier(
                dto.getBuilding(),
                dto.getFloor(),
                dto.getRoomNumber()
        );
    }
}
