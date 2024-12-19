package de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.repositories.adapters;

import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.RoomIdentifier;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.dao.interfaces.RoomIdentifierDAO;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.entities.RoomIdentifierEntity;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.mapper.RoomIdentifierMapper;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.repositories.interfaces.RoomIdentifierRepository;
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
