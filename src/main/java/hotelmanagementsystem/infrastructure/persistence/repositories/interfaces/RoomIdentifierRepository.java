package hotelmanagementsystem.infrastructure.persistence.repositories.interfaces;

import hotelmanagementsystem.domain.models.RoomIdentifier;

public interface RoomIdentifierRepository {
    void saveRoomIdentifier(RoomIdentifier roomIdentifier);
}
