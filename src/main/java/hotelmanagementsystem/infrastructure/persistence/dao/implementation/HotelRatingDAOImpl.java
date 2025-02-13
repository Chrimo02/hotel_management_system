package hotelmanagementsystem.infrastructure.persistence.dao.implementation;

import hotelmanagementsystem.infrastructure.persistence.dao.interfaces.HotelRatingDAO;
import hotelmanagementsystem.infrastructure.persistence.entities.HotelRatingEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@ApplicationScoped
public class HotelRatingDAOImpl implements HotelRatingDAO {

    @Inject
    EntityManager em;

    @Override
    @Transactional
    public Optional<HotelRatingEntity> createRating(HotelRatingEntity rating) {
        try {
            em.persist(rating);
            return Optional.of(rating);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessException("Error saving HotelRating", e);
        }
    }

    @Override
    @Transactional(Transactional.TxType.SUPPORTS)
    public Optional<HotelRatingEntity> findById(Long id) {
        try {
            HotelRatingEntity entity = em.find(HotelRatingEntity.class, id);
            return entity != null ? Optional.of(entity) : Optional.empty();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessException("Error while finding HotelRating by ID", e);
        }
    }

    @Override
    @Transactional(Transactional.TxType.SUPPORTS)
    public Optional<Map<Long, HotelRatingEntity>> findAll() {
        try {
            TypedQuery<HotelRatingEntity> query = em.createQuery(
                    "SELECT r FROM HotelRatingEntity r", HotelRatingEntity.class
            );
            List<HotelRatingEntity> resultList = query.getResultList();

            if (resultList.isEmpty()) {
                return Optional.empty();
            }

            Map<Long, HotelRatingEntity> ratingMap = resultList.stream()
                    .collect(Collectors.toMap(HotelRatingEntity::getId, Function.identity()));

            return Optional.of(ratingMap);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessException("Error while finding all HotelRatings", e);
        }
    }

    @Override
    @Transactional(Transactional.TxType.SUPPORTS)
    public Optional<List<HotelRatingEntity>> findFilteredRatings(long hotelID, int starRating, boolean onlyWithComment) {
        try {
            String queryString =
                    "SELECT r FROM HotelRatingEntity r WHERE r.hotelId = :hotelID";

            if (starRating > 0) {
                queryString += " AND r.starRating = :starRating";
            }
            if (onlyWithComment) {
                queryString += " AND r.commentRating IS NOT NULL AND r.commentRating != ''";
            }

            TypedQuery<HotelRatingEntity> query = em.createQuery(queryString, HotelRatingEntity.class);
            query.setParameter("hotelID", hotelID);

            if (starRating > 0) {
                query.setParameter("starRating", starRating);
            }


            List<HotelRatingEntity> resultList = query.getResultList();

            if (resultList.isEmpty()) {
                return Optional.empty();
            }
            return Optional.of(resultList);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessException("Error finding filtered hotel ratings", e);
        }
    }

    @Override
    @Transactional
    public void updateRating(HotelRatingEntity rating) {
        try {
            em.merge(rating);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessException("Error updating HotelRating", e);
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        try {
            HotelRatingEntity entity = em.find(HotelRatingEntity.class, id);
            if (entity != null) {
                em.remove(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessException("Error deleting HotelRating", e);
        }
    }
}
