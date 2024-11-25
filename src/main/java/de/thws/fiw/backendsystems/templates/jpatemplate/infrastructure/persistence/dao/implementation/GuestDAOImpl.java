package de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.dao.implementation;

import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.dao.interfaces.GuestDAO;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.entities.GuestEntity;
import jakarta.persistence.EntityManager;

public class GuestDAOImpl implements GuestDAO {
    public EntityManager entityManager() {
        return JpaUtil.getEntityManagerFactory().createEntityManager();
    }
    @Override
    public void create(GuestEntity guestEntity){
        EntityManager em = null;
        try {
            em = entityManager();
            em.getTransaction().begin();
            em.persist(guestEntity);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new DataAccessException("Error while creating guest entity, rolled back", e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    @Override
    public GuestEntity read(long id) {
        try (EntityManager em = entityManager()) {
            return em.find(GuestEntity.class, id);
        } catch (Exception e) {
            throw new DataAccessException("Error while reading guest entity!", e);
        }
    }
    @Override
    public void update(GuestEntity guestEntity) {
        EntityManager em = null;
        try {
            em = entityManager();
            em.getTransaction().begin();
            em.merge(guestEntity);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new DataAccessException("Error while updating guest entity, rolled back", e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void delete(GuestEntity guestEntity) {
        EntityManager em = null;
        try {
            em = entityManager();
            em.getTransaction().begin();
            em.remove(guestEntity);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new DataAccessException("Error while deleting guest entity, rolled back", e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
