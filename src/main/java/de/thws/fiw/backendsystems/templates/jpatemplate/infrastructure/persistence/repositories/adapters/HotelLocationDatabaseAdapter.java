package de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.repositories.adapters;

import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.Hotel;
import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.HotelLocation;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.dao.interfaces.HotelLocationDAO;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.entities.HotelEntity;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.entities.HotelLocationEntity;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.repositories.interfaces.HotelLocationRepository;

import java.util.List;

public class HotelLocationDatabaseAdapter implements HotelLocationRepository {
// hier werden daos aufgerufen
    private final HotelLocationDAO hotelLocationDAO;

    public HotelLocationDatabaseAdapter(HotelLocationDAO hotelLocationDAO) {
        this.hotelLocationDAO = hotelLocationDAO;
    }

    @Override
    public void createHotelLocation(HotelLocation hotelLocation) {
        HotelLocationEntity hotelLocationEntity = mapToEntity(hotelLocation);
        hotelLocationDAO.create(hotelLocationEntity);
    }

    @Override
    public void updateAddress(long hotelId, String newAddress) {
        HotelLocationEntity hotelLocationEntity = getHotelLocationById(hotelId, HotelLocationEntity.class);
        hotelLocationEntity.setAddress(newAddress);
        hotelLocationDAO.update(hotelLocationEntity);
    }

    @Override
    public void updateCity(long hotelId, String newCity) {
        HotelLocationEntity hotelLocationEntity = getHotelLocationById(hotelId, HotelLocationEntity.class);
        hotelLocationEntity.setCity(newCity);
        hotelLocationDAO.update(hotelLocationEntity);
    }

    @Override
    public void updateCountry(long hotelId, String newCountry) {
        HotelLocationEntity hotelLocationEntity = getHotelLocationById(hotelId, HotelLocationEntity.class);
        hotelLocationEntity.setCountry(newCountry);
        hotelLocationDAO.update(hotelLocationEntity);
    }

    @Override
    public void deleteHotelLocation(long hotelLocationId) {
        HotelLocationEntity hotelLocationEntity = hotelLocationDAO.read(hotelLocationId);
        hotelLocationDAO.delete(hotelLocationEntity);
    }

    @Override
    public <T> T getHotelLocationById(long id, Class<T> returnType) {
        HotelLocationEntity hotelLocationEntity = hotelLocationDAO.read(id);

        if (returnType.isAssignableFrom(HotelLocationEntity.class)) {
            return returnType.cast(hotelLocationEntity);
        } else if (returnType.isAssignableFrom(HotelLocation.class)) {
            return returnType.cast(mapToDomain(hotelLocationEntity));
        } else {
            throw new IllegalArgumentException("Unsupported return type: " + returnType.getName());
        }
    }

    @Override
    public List<HotelLocation> findAll() {
        return hotelLocationDAO.readAll()
                .stream()
                .map(this::mapToDomain) // Convert each HotelLocationEntity to HotelLocation
                .toList(); // Collect results into a List
    }



    private HotelLocationEntity mapToEntity(HotelLocation hotelLocation) {
        return new HotelLocationEntity.HotelLocationBuilder()
                    .withCity(hotelLocation.getCity())
                        .withAddress(hotelLocation.getAddress())
                            .withCountry(hotelLocation.getCountry())
                                .build();

    }

    private HotelLocation mapToDomain(HotelLocationEntity hotelLocationEntity) {
        return new HotelLocation.HotelLocationBuilder()
                    .withCity(hotelLocationEntity.getCity())
                        .withAddress(hotelLocationEntity.getAddress())
                            .withCountry(hotelLocationEntity.getCountry())
                                .build();

    }
}
