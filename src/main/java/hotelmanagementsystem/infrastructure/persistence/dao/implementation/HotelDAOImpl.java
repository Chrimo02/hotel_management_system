package hotelmanagementsystem.infrastructure.persistence.dao.implementation;

import hotelmanagementsystem.infrastructure.persistence.dao.interfaces.HotelDAO;
import hotelmanagementsystem.infrastructure.persistence.entities.HotelEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;
@ApplicationScoped
public class HotelDAOImpl implements HotelDAO {

    @Inject
    public EntityManager entityManager() {

      return JpaUtil.getEntityManagerFactory().createEntityManager();
    }

    @Override
    public HotelEntity createHotel(HotelEntity hotel) {
        EntityManager entityManager = null;
        try {
            entityManager = entityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(hotel);
            entityManager.getTransaction().commit();
            return hotel;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new DataAccessException("Error while creating hotel entity, rolled back", e);
        }
        finally {
            entityManager.close();
        }
    }

    @Override
    public Optional<HotelEntity> findById(Long id) {
        try {
            EntityManager entityManager = entityManager();
            HotelEntity entity = entityManager.find(HotelEntity.class, id);
            return entity != null ? Optional.of(entity) : Optional.empty();
        } catch (Exception e) {
            throw new DataAccessException("Error while finding hotel by ID", e);
        } finally {
            entityManager().close();
        }

    }
/* alt -> war vor filtering
    @Override
    public List<HotelEntity> findAll() {
        EntityManager entityManager = entityManager();
        TypedQuery<HotelEntity> query = entityManager.createQuery("SELECT h FROM HotelEntity h", HotelEntity.class);
        return query.getResultList();
    }

 */

    @Override
    public void updateHotel(HotelEntity hotel) {
        EntityManager entityManager = entityManager();
            try {
                entityManager.getTransaction().begin();
                entityManager.merge(hotel);
                entityManager.getTransaction().commit();
            } catch (Exception e) {
                if (entityManager.getTransaction().isActive()) {
                    entityManager.getTransaction().rollback();
                }
                throw new DataAccessException("Error updating Hotel", e);
            }
            finally {
                entityManager.close();
            }
    }

    @Override
    public void deleteById(Long id) {
        EntityManager entityManager = entityManager();
        try {
            entityManager.getTransaction().begin();
            HotelEntity entity = entityManager.find(HotelEntity.class, id);
            if (entity != null) {
                entityManager.remove(entity);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new DataAccessException("Error deleting Hotel", e);

    } finally {
        entityManager.close();
    }
    }

    // --------------------------------------------
    //  METHODEN für Filter + Paging
    // --------------------------------------------

    @Override
    public long countByFilter(String city, double minRating) {
        EntityManager em = null;
        try {
            em = entityManager();
            em.getTransaction().begin();

            String jpqlCount =
                    "SELECT COUNT(h) FROM HotelEntity h " +
                            "WHERE (:city IS NULL OR h.location.city = :city) " +
                            "  AND (h.averageRating >= :minRating)";

            TypedQuery<Long> countQuery = em.createQuery(jpqlCount, Long.class);
            countQuery.setParameter("city", (city == null || city.isBlank()) ? null : city);
            countQuery.setParameter("minRating", minRating);

            long total = countQuery.getSingleResult();

            em.getTransaction().commit();
            return total;

        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new DataAccessException("Error counting hotels by filter", e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<HotelEntity> findByFilter(String city, double minRating, int offset, int limit) {
        EntityManager em = null;
        try {
            em = entityManager();
            em.getTransaction().begin();

            String jpqlSelect =
                    "SELECT h FROM HotelEntity h " +
                            "WHERE (:city IS NULL OR h.location.city = :city) " +
                            "  AND (h.averageRating >= :minRating) " +
                            "ORDER BY h.id ASC";

            TypedQuery<HotelEntity> query = em.createQuery(jpqlSelect, HotelEntity.class);
            query.setParameter("city", (city == null || city.isBlank()) ? null : city);
            query.setParameter("minRating", minRating);

            // Paging
            query.setFirstResult(offset);   // Startindex
            query.setMaxResults(limit);     // Anzahl pro Seite

            List<HotelEntity> results = query.getResultList();

            em.getTransaction().commit();
            return results;
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new DataAccessException("Error retrieving hotels by filter with paging", e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }


}
