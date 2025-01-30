package hotelmanagementsystem.infrastructure.persistence.dao.implementation;

import hotelmanagementsystem.infrastructure.persistence.dao.interfaces.RoomDAO;
import hotelmanagementsystem.infrastructure.persistence.entities.RoomEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class RoomDAOImpl implements RoomDAO {

    @Inject
    EntityManager em;

    @Override
    @Transactional
    public RoomEntity create(RoomEntity roomEntity) {
        try {
            em.persist(roomEntity);
            return roomEntity.getId();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessException("Error creating RoomEntity", e);
        }
    }

    @Override
    @Transactional(Transactional.TxType.SUPPORTS)
    public RoomEntity read(long roomId) {
        try {
            return em.find(RoomEntity.class, roomId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessException("Error reading RoomEntity with ID=" + roomId, e);
        }
    }

    @Override
    @Transactional
    public void update(RoomEntity roomEntity) {
        try {
            em.merge(roomEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessException("Error updating RoomEntity", e);
        }
    }

    @Override
    @Transactional
    public void delete(RoomEntity roomEntity) {
        try {
            em.remove(em.contains(roomEntity) ? roomEntity : em.merge(roomEntity));
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessException("Error deleting RoomEntity", e);
        }
    }
}
