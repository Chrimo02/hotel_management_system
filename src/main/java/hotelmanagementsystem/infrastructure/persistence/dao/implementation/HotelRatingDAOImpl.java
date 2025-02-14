package hotelmanagementsystem.infrastructure.persistence.dao.implementation;

import hotelmanagementsystem.infrastructure.persistence.dao.interfaces.HotelRatingDAO;
import hotelmanagementsystem.infrastructure.persistence.entities.HotelRatingEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class HotelRatingDAOImpl implements HotelRatingDAO {

    @Inject
    EntityManager em;

    @Override
    @Transactional
    public HotelRatingEntity createRating(HotelRatingEntity rating) {
        try {
            em.persist(rating);
            return rating;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessException("Error saving HotelRating", e);
        }
    }

    @Override
    @Transactional(Transactional.TxType.SUPPORTS)
    public HotelRatingEntity findById(Long id) {
        try {
            HotelRatingEntity entity = em.find(HotelRatingEntity.class, id);
            return entity;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessException("Error while finding HotelRating by ID", e);
        }
    }

    @Override
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<HotelRatingEntity> findFilteredRatings(long hotelID, int starRating, boolean onlyWithComment) {
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

            return resultList;
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
