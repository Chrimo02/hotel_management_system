package hotelmanagementsystem.infrastructure.persistence.dao.implementation;

import hotelmanagementsystem.infrastructure.persistence.dao.interfaces.RoomIdentifierDAO;
import hotelmanagementsystem.infrastructure.persistence.entities.RoomIdentifierEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class RoomIdentifierDAOImpl implements RoomIdentifierDAO {

    @Inject
    EntityManager em;

    @Override
    @Transactional
    public void create(RoomIdentifierEntity entity) {
        try {
            em.persist(entity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessException("Error creating RoomIdentifierEntity", e);
        }
    }
}
