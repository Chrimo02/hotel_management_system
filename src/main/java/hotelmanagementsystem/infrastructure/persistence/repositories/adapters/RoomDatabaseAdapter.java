package hotelmanagementsystem.infrastructure.persistence.repositories.adapters;

import hotelmanagementsystem.domain.models.Room;
import hotelmanagementsystem.infrastructure.persistence.dao.interfaces.RoomDAO;
import hotelmanagementsystem.infrastructure.persistence.entities.RoomEntity;
import hotelmanagementsystem.infrastructure.persistence.mapper.BookingMapper;
import hotelmanagementsystem.infrastructure.persistence.mapper.RoomIdentifierMapper;
import hotelmanagementsystem.infrastructure.persistence.mapper.RoomMapper;
import hotelmanagementsystem.infrastructure.persistence.repositories.interfaces.RoomRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class RoomDatabaseAdapter implements RoomRepository {
    private final RoomMapper roomMapper;
    private final RoomDAO roomDAO;
    private final RoomIdentifierMapper roomIdentifierMapper;
    private final BookingMapper bookingMapper;

    @Inject
    public RoomDatabaseAdapter(RoomDAO roomDAO, RoomMapper roomMapper, RoomIdentifierMapper roomIdentifierMapper, BookingMapper bookingMapper) {
        this.roomDAO = roomDAO;
        this.roomMapper = roomMapper;
        this.roomIdentifierMapper = roomIdentifierMapper;
        this.bookingMapper = bookingMapper;
    }
    @Override
    public Room findRoomById(long roomId) {
        RoomEntity roomEntity = roomDAO.read(roomId);
        return roomMapper.entityToDomain(roomEntity);
    }
    @Override
    public long saveRoom(Room room) {
        RoomEntity entity = roomMapper.domainToEntity(room);
        return roomDAO.create(entity);
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
    @Override
    public void updateRoom(Room room) {
        RoomEntity roomEntity = roomDAO.read(room.getId());
        if (roomEntity != null) {
            roomEntity.setPricePerNight(room.getPricePerNight());

            roomEntity.setRoomIdentifier(roomIdentifierMapper.domainToEntity(room.getRoomIdentifier()));

            roomEntity.setBookings(bookingMapper.toEntitySet(room.getBookings()));

            roomDAO.update(roomEntity);
        }
    }
}
