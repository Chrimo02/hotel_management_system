package de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.repositories.adapters;

import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.Room;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.dao.interfaces.RoomDAO;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.entities.RoomEntity;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.mapper.RoomMapper;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.repositories.interfaces.RoomRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class RoomDatabaseAdapter implements RoomRepository {
    private final RoomMapper roomMapper;
    private final RoomDAO roomDAO;
    @Inject
    public RoomDatabaseAdapter(RoomDAO roomDAO, RoomMapper roomMapper) {
        this.roomDAO = roomDAO;
        this.roomMapper = roomMapper;
    }
    @Override
    public Room findRoomById(long roomId) {
        RoomEntity roomEntity = roomDAO.read(roomId);
        return roomMapper.entityToDomain(roomEntity);
    }
    @Override
    public void saveRoom(Room room) {
        RoomEntity entity = roomMapper.domainToEntity(room);
        roomDAO.create(entity);
    }
    @Override
    public void removeRoom(long roomId) {
        RoomEntity roomEntity = roomDAO.read(roomId);
        roomDAO.delete(roomEntity);
    }
    public void updatePrice(long roomId, double pricePerNight) {
        RoomEntity roomEntity = roomDAO.read(roomId);
        roomEntity.setPricePerNight(pricePerNight);
        roomDAO.update(roomEntity);
    }
}
