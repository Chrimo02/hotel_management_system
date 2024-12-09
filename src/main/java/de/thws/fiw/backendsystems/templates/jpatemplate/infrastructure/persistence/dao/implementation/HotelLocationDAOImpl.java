package de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.dao.implementation;

import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.HotelLocation;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.dao.interfaces.HotelLocationDAO;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.entities.HotelLocationEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class HotelLocationDAOImpl implements HotelLocationDAO {
    //manager for managing databse entries
    public EntityManager entityManager() {
        return JpaUtil.getEntityManagerFactory().createEntityManager();
    }
    //jpa h2 transactions
    @Override
    public void create(HotelLocationEntity hotelLocation) {
        EntityManager em = null;
        try {
            em = entityManager();
            em.getTransaction().begin();
            em.persist(hotelLocation);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new DataAccessException("Error while creating HotelLocation entity, rolled back", e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public HotelLocationEntity read(long id) {
        try (EntityManager em = entityManager()) {
            return em.find(HotelLocationEntity.class, id);
        } catch (Exception e) {
            throw new DataAccessException("Error while reading HotelLocation entity!", e);
        }
    }

    @Override
    public List<HotelLocationEntity> readAll() {
        EntityManager em = entityManager(); // Open EntityManager
        try {

            // Use JPQL to fetch all HotelLocationEntity entries
            return em.createQuery("SELECT h FROM HotelLocationEntity h", HotelLocationEntity.class).getResultList();
        } catch (Exception e) {
            throw new DataAccessException("Error while reading all HotelLocation entities!", e);
        } finally {
            if (em != null) {
                em.close(); // Ensure the EntityManager is closed
            }
        }
    }

    @Override
    public void update(HotelLocationEntity hotelLocation) {
        EntityManager em = null;
        try {
            em = entityManager();
            em.getTransaction().begin();
            em.merge(hotelLocation);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new DataAccessException("Error while updating HotelLocation entity, rolled back", e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void delete(HotelLocationEntity hotelLocation) {
        EntityManager em = null;
        try {
            em = entityManager();
            em.getTransaction().begin();
            em.remove(em.contains(hotelLocation) ? hotelLocation : em.merge(hotelLocation));
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new DataAccessException("Error while deleting HotelLocation entity, rolled back", e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public HotelLocationEntity findLocationByHotelId(long hotelId) {
        EntityManager em = entityManager();
        try {
            // JPQL-Abfrage: Nur die Location für die Hotel-ID abfragen
            TypedQuery<HotelLocationEntity> query = em.createQuery(
                    "SELECT h.location FROM HotelEntity h WHERE h.id = :hotelId",
                    HotelLocationEntity.class
            );
            query.setParameter("hotelId", hotelId);
            return query.getSingleResult(); // Einzelnes Ergebnis abrufen
        } catch (NoResultException e) {
            throw new IllegalArgumentException("No location found for Hotel ID: " + hotelId, e);
        } finally {
            em.close();
        }
    }
}
