package hotelmanagementsystem.infrastructure.persistence.dao.implementation;

import hotelmanagementsystem.infrastructure.persistence.dao.interfaces.HotelDAO;
import hotelmanagementsystem.infrastructure.persistence.entities.HotelEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class HotelDAOImpl implements HotelDAO {

    @Inject
    EntityManager em;

    @Override
    @Transactional
    public HotelEntity createHotel(HotelEntity hotel) {
        try {
            em.persist(hotel);
            return hotel;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessException("Error while creating hotel entity", e);
        }
    }

    @Override
    @Transactional(Transactional.TxType.SUPPORTS)
    public Optional<HotelEntity> findById(Long id) {
        try {
            HotelEntity entity = em.find(HotelEntity.class, id);
            return entity != null ? Optional.of(entity) : Optional.empty();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessException("Error while finding hotel by ID", e);
        }
    }

    @Override
    @Transactional
    public HotelEntity updateHotel(HotelEntity hotel) {
        try {
            em.merge(hotel);
            return hotel;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessException("Error updating Hotel", e);
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        try {
            HotelEntity entity = em.find(HotelEntity.class, id);
            if (entity != null) {
                em.remove(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessException("Error deleting Hotel", e);
        }
    }

    // --------------------------------------------
    //  METHODEN für Filter + Paging
    // --------------------------------------------

    @Override
    @Transactional(Transactional.TxType.SUPPORTS)
    public long countByFilter(String city, double minRating) {
        try {
            String jpqlCount =
                    "SELECT COUNT(h) FROM HotelEntity h " +
                            "WHERE (:city IS NULL OR h.location.city = :city) " +
                            "  AND (h.averageRating >= :minRating)";

            TypedQuery<Long> countQuery = em.createQuery(jpqlCount, Long.class);
            countQuery.setParameter("city", (city == null || city.isBlank()) ? null : city);
            countQuery.setParameter("minRating", minRating);

            return countQuery.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessException("Error counting hotels by filter", e);
        }
    }

    @Override
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<HotelEntity> findByFilter(String city, double minRating, int offset, int limit) {
        try {
            String jpqlSelect =
                    "SELECT h FROM HotelEntity h " +
                            "WHERE (:city IS NULL OR h.location.city = :city) " +
                            "  AND (h.averageRating >= :minRating) " +
                            "ORDER BY h.id ASC";

            TypedQuery<HotelEntity> query = em.createQuery(jpqlSelect, HotelEntity.class);
            query.setParameter("city", (city == null || city.isBlank()) ? null : city);
            query.setParameter("minRating", minRating);

            // Paging
            query.setFirstResult(offset);
            query.setMaxResults(limit);

            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessException("Error retrieving hotels by filter with paging", e);
        }
    }
}
