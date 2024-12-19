package de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.dao.implementation;

import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.dao.interfaces.BookingDAO;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.entities.BookingEntity;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.entities.GuestEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class BookingDAOImpl implements BookingDAO {
    @Override
    public EntityManager entityManager() {
        return JpaUtil.getEntityManagerFactory().createEntityManager();
    }

    @Override
    public BookingEntity create(BookingEntity bookingEntity) {
        EntityManager em = null;
        try {
            em = entityManager();
            em.getTransaction().begin();
            em.persist(bookingEntity);
            em.getTransaction().commit();
            return bookingEntity;
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new DataAccessException("Error while creating booking entity, rolled back", e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public BookingEntity read(long id) {
        try (EntityManager em = entityManager()) {
            return em.find(BookingEntity.class, id);
        } catch (Exception e) {
            throw new DataAccessException("Error while reading booking entity!", e);
        }
    }

    @Override
    public void update(BookingEntity bookingEntity) {
        EntityManager em = null;
        try {
            em = entityManager();
            em.getTransaction().begin();
            em.merge(bookingEntity);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new DataAccessException("Error while updating booking entity, rolled back", e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
