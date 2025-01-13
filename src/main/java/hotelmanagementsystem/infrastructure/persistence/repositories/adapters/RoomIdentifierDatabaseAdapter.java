package hotelmanagementsystem.infrastructure.persistence.repositories.adapters;

import hotelmanagementsystem.domain.models.RoomIdentifier;
import hotelmanagementsystem.infrastructure.persistence.dao.interfaces.RoomIdentifierDAO;
import hotelmanagementsystem.infrastructure.persistence.entities.RoomIdentifierEntity;
import hotelmanagementsystem.infrastructure.persistence.mapper.RoomIdentifierMapper;
import hotelmanagementsystem.infrastructure.persistence.repositories.interfaces.RoomIdentifierRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class RoomIdentifierDatabaseAdapter implements RoomIdentifierRepository {
    private final RoomIdentifierDAO roomIdentifierDAO;
    private final RoomIdentifierMapper roomIdentifierMapper;
    @Inject
    public RoomIdentifierDatabaseAdapter(RoomIdentifierDAO roomIdentifierDAO, RoomIdentifierMapper roomIdentifierMapper) {
        this.roomIdentifierDAO = roomIdentifierDAO;
        this.roomIdentifierMapper = roomIdentifierMapper;
    }
    @Override
    public void saveRoomIdentifier(RoomIdentifier roomIdentifier) {
        RoomIdentifierEntity entity = roomIdentifierMapper.domainToEntity(roomIdentifier);
        roomIdentifierDAO.create(entity);
    }
}
