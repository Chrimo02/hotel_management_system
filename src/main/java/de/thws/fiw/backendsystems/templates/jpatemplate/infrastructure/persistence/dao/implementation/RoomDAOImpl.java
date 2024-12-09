package de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.dao.implementation;

import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.dao.interfaces.RoomDAO;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.entities.RoomEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
@ApplicationScoped
public class RoomDAOImpl implements RoomDAO {
    EntityManagerFactory emf;
    @Inject
    public RoomDAOImpl() {
        emf = Persistence.createEntityManagerFactory("my-persistence-unit");
    }
    public void create(RoomEntity roomEntity) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(roomEntity);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
    public RoomEntity read(long roomId) {
        try(EntityManager em = emf.createEntityManager()) {
            RoomEntity roomEntity = em.find(RoomEntity.class, roomId);
            return roomEntity;
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