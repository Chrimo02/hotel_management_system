package de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.repositories.adapters;

import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.Hotel;
import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.HotelLocation;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.dao.interfaces.HotelDAO;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.entities.HotelEntity;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.entities.HotelLocationEntity;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.repositories.interfaces.HotelRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class HotelDatabaseAdapter implements HotelRepository {

    private final HotelDAO hotelDAO;

    public HotelDatabaseAdapter(HotelDAO hotelDAO) {
        this.hotelDAO = hotelDAO;
    }

    @Override
    public Hotel createHotel(Hotel hotel) {
        try {
            return mapToDomain(hotelDAO.createHotel(mapToEntity(hotel)));
        } catch (Exception e) {
            throw new RuntimeException("Error saving Hotel", e);
        }
    }

    @Override
    public Optional<Hotel> findById(Long id) {
        return hotelDAO.findById(id)
                .map(this::mapToDomain);
    }

    @Override
    public List<Hotel> findAll() {
        // Retrieve all entities from the DAO and map them to domain objects
        return hotelDAO.findAll().stream()
                .map(this::mapToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void update(Hotel hotel) {
        try {
            // Map the domain object to the entity and pass it to the DAO for updating
            hotelDAO.update(mapToEntity(hotel));
        } catch (Exception e) {
            throw new RuntimeException("Error updating Hotel", e);
        }
    }

    @Override
    public void deleteById(Long id) {
        try {
            // Use the DAO to delete the hotel by ID
            hotelDAO.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting Hotel", e);
        }
    }

    private HotelEntity mapToEntity(Hotel hotel) {
        return new HotelEntity.HotelBuilder()
                .withId(hotel.getId())
                .withName(hotel.getName())
                .withDescription(hotel.getDescription())
                .withLocation(mapLocationToEntity(hotel.getLocation()))
                .build();
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
        return new HotelLocationEntity.HotelLocationBuilder()
                .withAddress(location.getAddress())
                .withCity(location.getCity())
                .withCountry(location.getCountry())
                .build();
    }

    private HotelLocation mapLocationToDomain(HotelLocationEntity entity) {
        if (entity == null) return null;
        return new HotelLocation.HotelLocationBuilder(entity.getId())
                .withAddress(entity.getAddress())
                .withCity(entity.getCity())
                .withCountry(entity.getCountry())
                .build();
    }
}
