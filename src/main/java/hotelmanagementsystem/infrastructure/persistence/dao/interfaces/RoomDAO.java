package hotelmanagementsystem.infrastructure.persistence.dao.interfaces;

import hotelmanagementsystem.infrastructure.persistence.entities.RoomEntity;

public interface RoomDAO {
    long create(RoomEntity roomEntity);
    RoomEntity read(long roomId);
    void update(RoomEntity roomEntity);
    void delete(RoomEntity roomEntity);
}