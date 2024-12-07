package de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.repositories.adapters;

import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.Hotel;
import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.HotelLocation;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.entities.HotelEntity;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.entities.HotelLocationEntity;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.repositories.interfaces.HotelRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class HotelDatabaseAdapter implements HotelRepository {

    private final EntityManager entityManager;

    public HotelDatabaseAdapter(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public boolean createHotel(Hotel hotel) {
        HotelEntity entity = mapToEntity(hotel);
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(entity);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException("Error saving Hotel", e);
        }
    }

    @Override
    public Optional<Hotel> findById(Long id) {
        HotelEntity entity = entityManager.find(HotelEntity.class, id);
        return entity != null ? Optional.of(mapToDomain(entity)) : Optional.empty();
    }

    @Override
    public List<Hotel> findAll() {
        TypedQuery<HotelEntity> query = entityManager.createQuery("SELECT h FROM HotelEntity h", HotelEntity.class);
        return query.getResultList().stream().map(this::mapToDomain).collect(Collectors.toList());
    }

    @Override
    public void update(Hotel hotel) {
        HotelEntity entity = mapToEntity(hotel);
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(entity);
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

    private HotelEntity mapToEntity(Hotel hotel) {
        HotelEntity entity = new HotelEntity();
        entity.setId(hotel.getId());
        entity.setName(hotel.getName());
        entity.setDescription(hotel.getDescription());
        entity.setLocation(mapLocationToEntity(hotel.getLocation()));
        return entity;
    }

    private Hotel mapToDomain(HotelEntity entity) {
        return new Hotel.HotelBuilder()
                .withName(entity.getName())
                .withDescription(entity.getDescription())
                .withLocation(mapLocationToDomain(entity.getLocation()))
                .build();
    }

    private HotelLocationEntity mapLocationToEntity(HotelLocation location) {
        if (location == null) return null;
        return new HotelLocationEntity.HotelLocationBuilder(null)
                .withAddress(location.getAddress())
                .withCity(location.getCity())
                .withCountry(location.getCountry())
                .build();
    }

    private HotelLocation mapLocationToDomain(HotelLocationEntity entity) {
        if (entity == null) return null;
        return new HotelLocation(entity.getId(), entity.getAddress(), entity.getCity(), entity.getCountry());
    }
}
