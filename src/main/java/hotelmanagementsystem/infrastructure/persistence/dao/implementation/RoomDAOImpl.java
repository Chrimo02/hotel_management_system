package hotelmanagementsystem.infrastructure.persistence.dao.implementation;

import hotelmanagementsystem.infrastructure.persistence.dao.interfaces.RoomDAO;
import hotelmanagementsystem.infrastructure.persistence.entities.RoomEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

@ApplicationScoped
public class RoomDAOImpl implements RoomDAO {
    EntityManagerFactory emf;
    @Inject
    public RoomDAOImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }
    public long create(RoomEntity roomEntity) {
        EntityManager em = emf.createEntityManager();
        long id = -1;
        try {
            em.getTransaction().begin();
            em.persist(roomEntity);
            em.getTransaction().commit();
            id = roomEntity.getId();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        return id;
    }
    @Override
    public RoomEntity read(long roomId) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.find(RoomEntity.class, roomId); // Automatische Typ-Erkennung durch Hibernate
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public void update(RoomEntity roomEntity) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(roomEntity);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
    public void delete(RoomEntity roomEntity) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(roomEntity);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}