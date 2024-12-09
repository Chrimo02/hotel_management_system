package de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.repositories.adapters;

import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.Room;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.dao.interfaces.RoomDAO;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.entities.RoomEntity;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.repositories.interfaces.RoomRepository;

public class RoomDatabaseAdapter implements RoomRepository {
    private final RoomDAO roomDAO;
    @Inject
    public RoomDatabaseAdapter(RoomDAO roomDAO) {
        this.roomDAO = roomDAO;
    }
    @Override
    public Room findRoomById(long roomId, ) {
        RoomEntity roomEntity = roomDAO.read(roomId);
        return entityToDomain(roomEntity);
    }
    @Override
    public void saveRoom(Room room) {
        RoomEntity entity = domainToEntity(room);
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

    private Room entityToDomain(RoomEntity entity, Class<? extends Room> roomType) {
        //entweder room nicht abstract machen, oder roomType als parameter auch übergeben
        //return new Room(entity.getId(), entity.getPricePerNight(), entity.getRoomIdentifier(), entity.getHotel())
    }
    private RoomEntity domainToEntity(Room room) {

    }
}
