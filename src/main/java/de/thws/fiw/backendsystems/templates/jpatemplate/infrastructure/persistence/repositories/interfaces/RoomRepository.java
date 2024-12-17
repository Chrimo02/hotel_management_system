package de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.repositories.interfaces;

import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.Room;

public interface RoomRepository {
    public Room findRoomById(long roomId);
    public void saveRoom(Room room);
    public void removeRoom(long roomId);
    public void updateRoom(Room room);

}