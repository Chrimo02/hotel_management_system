package hotelmanagementsystem.infrastructure.persistence.mapper;

import hotelmanagementsystem.domain.models.RoomIdentifier;
import hotelmanagementsystem.infrastructure.persistence.entities.RoomIdentifierEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class RoomIdentifierMapper {
    @Inject
    public RoomIdentifierMapper(){};
    public RoomIdentifierEntity domainToEntity(RoomIdentifier roomIdentifier) {
        if (roomIdentifier == null) return null;
        RoomIdentifierEntity entity = new RoomIdentifierEntity(
                roomIdentifier.getBuilding(),
                roomIdentifier.getFloor(),
                roomIdentifier.getRoomNumber()
        );
        if (roomIdentifier.getId() > 0){
            entity.setId(roomIdentifier.getId());
        }
        return entity;
    }
    public RoomIdentifier entityToDomain(RoomIdentifierEntity roomIdentifierEntity) {
        if (roomIdentifierEntity == null) return null;
        RoomIdentifier roomIdentifier = new RoomIdentifier(
                roomIdentifierEntity.getBuilding(),
                roomIdentifierEntity.getFloor(),
                roomIdentifierEntity.getRoomNumber()
        );
        roomIdentifier.setId(roomIdentifierEntity.getId());

        return roomIdentifier;
    }
}
