package de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.dao.implementation;

import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.dao.interfaces.GuestDAO;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.entities.GuestEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.List;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.entities.BookingEntity;
import jakarta.persistence.TypedQuery;

@ApplicationScoped
public class GuestDAOImpl implements GuestDAO {
    @Inject
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

    @Override
    public List<BookingEntity> findBookingsByGuestId(long guestId) {
        EntityManager em = null;
        try {
            em = entityManager();
            // JPQL-Abfrage, um alle Buchungen des Gastes zu finden, egal in welchem Hotel
            String jpql = "SELECT b FROM BookingEntity b JOIN b.guests g WHERE g.id = :guestId";
            TypedQuery<BookingEntity> query = em.createQuery(jpql, BookingEntity.class);
            query.setParameter("guestId", guestId);
            return query.getResultList();
        } catch (Exception e) {
            throw new DataAccessException("Error while retrieving bookings for guest ID: " + guestId, e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
