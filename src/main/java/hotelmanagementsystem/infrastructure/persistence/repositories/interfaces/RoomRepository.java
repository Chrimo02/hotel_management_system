package hotelmanagementsystem.infrastructure.persistence.repositories.interfaces;

import hotelmanagementsystem.domain.models.Room;

public interface RoomRepository {
    public Room findRoomById(long roomId);
    public Room saveRoom(Room room);
    public void removeRoom(long roomId);
    public void updateRoom(Room room);

}