package de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.repositories.interfaces;

import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.RoomIdentifier;

public interface RoomIdentifierRepository {
    void saveRoomIdentifier(RoomIdentifier roomIdentifier);
}
