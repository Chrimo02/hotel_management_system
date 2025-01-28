package hotelmanagementsystem.infrastructure.api.mapper;

import hotelmanagementsystem.domain.models.RoomIdentifier;
import hotelmanagementsystem.infrastructure.api.dto.RoomIdentifierDTO;

public class RoomIdentifierMapper {

    // --- Map Domain RoomIdentifier -> DTO ---
    public static RoomIdentifierDTO toDTO(RoomIdentifier roomIdentifier) {
        return new RoomIdentifierDTO(
                roomIdentifier.getBuilding(),
                roomIdentifier.getFloor(),
                roomIdentifier.getRoomNumber()
        );
    }

    // --- Map DTO -> Domain RoomIdentifier ---
    public static RoomIdentifier toDomain(RoomIdentifierDTO dto) {
        return new RoomIdentifier(
                dto.getBuilding(),
                dto.getFloor(),
                dto.getRoomNumber()
        );
    }
}
