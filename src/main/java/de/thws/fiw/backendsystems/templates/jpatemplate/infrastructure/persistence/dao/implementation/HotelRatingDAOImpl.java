package de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.dao.implementation;

import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.dao.interfaces.HotelRatingDAO;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.entities.HotelRatingEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;
@ApplicationScoped
public class HotelRatingDAOImpl implements HotelRatingDAO {

    private final EntityManager entityManager;
    @Inject
    public HotelRatingDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<HotelRatingEntity> createRating(HotelRatingEntity rating) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(rating);
            entityManager.getTransaction().commit();
            return Optional.of(rating);
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException("Error saving HotelRating", e);
        }
    }

    @Override
    public Optional<HotelRatingEntity> findById(Long id) {
        HotelRatingEntity entity = entityManager.find(HotelRatingEntity.class, id);
        return entity != null ? Optional.of(entity) : Optional.empty();
    }

    @Override
    public Optional<List<HotelRatingEntity>> findAll() {
        TypedQuery<HotelRatingEntity> query = entityManager.createQuery("SELECT r FROM HotelRatingEntity r", HotelRatingEntity.class);
        return Optional.of(query.getResultList());
    }

    @Override
    public Optional<List<HotelRatingEntity>> findFilteredRatings(long hotelID, int starRating, boolean onlyWithComment) {
        String queryString = "SELECT r FROM HotelRatingEntity r WHERE r.hotel.id = :hotelID AND r.starRating = :starRating";
        if (onlyWithComment) {
            queryString += " AND r.commentRating IS NOT NULL AND r.commentRating != ''";
        }

        TypedQuery<HotelRatingEntity> query = entityManager.createQuery(queryString, HotelRatingEntity.class);
        query.setParameter("hotelID", hotelID);
        query.setParameter("starRating", starRating);

        return Optional.of(query.getResultList());
    }


    @Override
    public void updateRating(HotelRatingEntity rating) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(rating);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException("Error updating HotelRating", e);
        }
    }

    @Override
    public void deleteById(Long id) {
        try {
            entityManager.getTransaction().begin();
            HotelRatingEntity entity = entityManager.find(HotelRatingEntity.class, id);
            if (entity != null) {
                entityManager.remove(entity);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException("Error deleting HotelRating", e);
        }
    }
}
