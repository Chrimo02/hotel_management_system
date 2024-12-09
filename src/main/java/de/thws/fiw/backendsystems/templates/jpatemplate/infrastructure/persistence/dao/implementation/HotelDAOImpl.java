package de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.dao.implementation;

import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.Hotel;
import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.HotelLocation;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.dao.interfaces.HotelDAO;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.entities.HotelEntity;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.entities.HotelLocationEntity;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.repositories.interfaces.HotelRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class HotelDAOImpl implements HotelDAO {

    private final EntityManager entityManager;

    public HotelDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public HotelEntity createHotel(HotelEntity hotel) {
        try {
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
    }

    @Override
    public Optional<HotelEntity> findById(Long id) {
        HotelEntity entity = entityManager.find(HotelEntity.class, id);
        return entity != null ? Optional.of(entity) : Optional.empty();
    }

    @Override
    public List<HotelEntity> findAll() {
        TypedQuery<HotelEntity> query = entityManager.createQuery("SELECT h FROM HotelEntity h", HotelEntity.class);
        return query.getResultList();
    }

    @Override
    public void updateHotel(HotelEntity hotel) {
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
    }

    @Override
    public void deleteById(Long id) {
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
        }
    }
}
