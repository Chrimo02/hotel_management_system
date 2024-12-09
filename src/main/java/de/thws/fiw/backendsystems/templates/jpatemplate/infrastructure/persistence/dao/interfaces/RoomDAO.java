package de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.dao.interfaces;

import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.entities.RoomEntity;
import jakarta.persistence.EntityManager;

public interface RoomDAO {
    void create(RoomEntity roomEntity);
    RoomEntity read(long roomId);
    void update(RoomEntity roomEntity);
    void delete(RoomEntity roomEntity);
}