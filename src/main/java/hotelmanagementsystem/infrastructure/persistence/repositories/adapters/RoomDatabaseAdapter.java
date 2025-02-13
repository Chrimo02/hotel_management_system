package hotelmanagementsystem.infrastructure.persistence.repositories.adapters;

import hotelmanagementsystem.domain.models.Room;
import hotelmanagementsystem.infrastructure.persistence.dao.interfaces.HotelDAO;
import hotelmanagementsystem.infrastructure.persistence.dao.interfaces.RoomDAO;
import hotelmanagementsystem.infrastructure.persistence.entities.HotelEntity;
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
    private final HotelDAO hotelDAO;
    private final RoomIdentifierMapper roomIdentifierMapper;
    private final BookingMapper bookingMapper;

    @Inject
    public RoomDatabaseAdapter(RoomDAO roomDAO, RoomMapper roomMapper, RoomIdentifierMapper roomIdentifierMapper, BookingMapper bookingMapper, HotelDAO hotelDAO ) {
        this.roomDAO = roomDAO;
        this.roomMapper = roomMapper;
        this.hotelDAO = hotelDAO;
        this.roomIdentifierMapper = roomIdentifierMapper;
        this.bookingMapper = bookingMapper;
    }
    @Override
    public Room findRoomById(long roomId) {
        RoomEntity roomEntity = roomDAO.read(roomId);
        return roomMapper.entityToDomain(roomEntity);
    }
    @Override
    public Room saveRoom(Room room) {
        RoomEntity entity = roomMapper.domainToEntity(room);
        return roomMapper.entityToDomain(roomDAO.create(entity));
    }
    @Override
    public void removeRoom(long roomId) {
        RoomEntity roomEntity = roomDAO.read(roomId);
        HotelEntity hotelEntity = hotelDAO.findById(roomEntity.getHotel().getId());
        hotelEntity.getRooms().remove(roomEntity);
        hotelDAO.updateHotel(hotelEntity);
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
