package de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.repositories.adapters;

import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.Room;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.dao.interfaces.RoomDAO;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.repositories.interfaces.RoomRepository;

public class RoomDatabaseAdapter implements RoomRepository {
    private final RoomDAO roomDAO;
    public RoomDatabaseAdapter(RoomDAO roomDAO) {
        this.roomDAO = roomDAO;
    }
    @Override
    public Room findRoomById(long roomId) {

    }
    @Override
    public void saveRoom(Room room) {

    }
    @Override
    public void removeRoom(long roomId) {

    }
}
