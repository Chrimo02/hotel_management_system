package de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.mapper;

import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.RoomIdentifier;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.entities.RoomIdentifierEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class RoomIdentifierMapper {
    @Inject
    public RoomIdentifierMapper(){};
    public RoomIdentifierEntity domainToEntity(RoomIdentifier roomIdentifier) {
        return new RoomIdentifierEntity(roomIdentifier.getBuilding(), roomIdentifier.getFloor(), roomIdentifier.getRoomNumber());
    }
    public RoomIdentifier entityToDomain(RoomIdentifierEntity roomIdentifierEntity) {
        return new RoomIdentifier(roomIdentifierEntity.getBuilding(),
                roomIdentifierEntity.getFloor(),
                roomIdentifierEntity.getRoomNumber());
    }
}
