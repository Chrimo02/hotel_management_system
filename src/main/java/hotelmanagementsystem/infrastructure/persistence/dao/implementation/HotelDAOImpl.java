package hotelmanagementsystem.infrastructure.persistence.dao.implementation;

import hotelmanagementsystem.infrastructure.persistence.dao.interfaces.HotelDAO;
import hotelmanagementsystem.infrastructure.persistence.entities.HotelEntity;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

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
            throw new RuntimeException("Error saving Hotel", e);
        }
        finally {
            entityManager.close();
        }
    }

    @Override
    public Optional<HotelEntity> findById(Long id) {
        EntityManager entityManager = entityManager();
        HotelEntity entity = entityManager.find(HotelEntity.class, id);
        return entity != null ? Optional.of(entity) : Optional.empty();
    }

    @Override
    public List<HotelEntity> findAll() {
        EntityManager entityManager = entityManager();
        TypedQuery<HotelEntity> query = entityManager.createQuery("SELECT h FROM HotelEntity h", HotelEntity.class);
        return query.getResultList();
    }

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
                throw new RuntimeException("Error updating Hotel", e);
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
            throw new RuntimeException("Error deleting Hotel", e);

    } finally {
        entityManager.close();
    }
    }
}
