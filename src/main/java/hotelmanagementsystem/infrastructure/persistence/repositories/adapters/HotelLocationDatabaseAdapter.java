package hotelmanagementsystem.infrastructure.persistence.repositories.adapters;

import hotelmanagementsystem.domain.models.HotelLocation;
import hotelmanagementsystem.infrastructure.persistence.dao.interfaces.HotelDAO;
import hotelmanagementsystem.infrastructure.persistence.dao.interfaces.HotelLocationDAO;
import hotelmanagementsystem.infrastructure.persistence.entities.HotelLocationEntity;
import hotelmanagementsystem.infrastructure.persistence.mapper.HotelLocationMapper;
import hotelmanagementsystem.infrastructure.persistence.repositories.interfaces.HotelLocationRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class HotelLocationDatabaseAdapter implements HotelLocationRepository {
    private final HotelLocationDAO hotelLocationDAO;
    private final HotelDAO hotelDAO;
    private final HotelLocationMapper hotelLocationMapper;
    @Inject
    public HotelLocationDatabaseAdapter(HotelLocationDAO hotelLocationDAO, HotelDAO hotelDAO, HotelLocationMapper hotelLocationMapper) {
        this.hotelLocationDAO = hotelLocationDAO;
        this.hotelDAO = hotelDAO;
        this.hotelLocationMapper = hotelLocationMapper;
    }

    @Override
    public void createHotelLocation(HotelLocation hotelLocation) {
        HotelLocationEntity hotelLocationEntity = hotelLocationMapper.mapToEntity(hotelLocation);
        hotelLocationDAO.create(hotelLocationEntity);
    }

    @Override
    public void updateAddress(long hotelId, String newAddress) {
        HotelLocationEntity hotelLocationEntity = getHotelLocationByHotelId(hotelId, HotelLocationEntity.class);
        hotelLocationEntity.setAddress(newAddress);
        hotelLocationDAO.update(hotelLocationEntity);
    }

    @Override
    public void updateCity(long hotelId, String newCity) {
        HotelLocationEntity hotelLocationEntity = getHotelLocationByHotelId(hotelId, HotelLocationEntity.class);
        hotelLocationEntity.setCity(newCity);
        hotelLocationDAO.update(hotelLocationEntity);
    }

    @Override
    public void updateCountry(long hotelId, String newCountry) {
        HotelLocationEntity hotelLocationEntity = getHotelLocationByHotelId(hotelId, HotelLocationEntity.class);
        hotelLocationEntity.setCountry(newCountry);
        hotelLocationDAO.update(hotelLocationEntity);
    }

    @Override
    public void deleteHotelLocation(long hotelLocationId) {
        HotelLocationEntity hotelLocationEntity = hotelLocationDAO.read(hotelLocationId);
        hotelLocationDAO.delete(hotelLocationEntity);
    }

    @Override
    public <T> T getHotelLocationByHotelId(long hotelId, Class<T> returnType) {
        HotelLocationEntity hotelLocationEntity = hotelDAO.findById(hotelId).get().getLocation();

        if (returnType.isAssignableFrom(HotelLocationEntity.class)) {
            return returnType.cast(hotelLocationEntity);
        } else if (returnType.isAssignableFrom(HotelLocation.class)) {
            return returnType.cast(hotelLocationMapper.mapToDomain(hotelLocationEntity));
        } else {
            throw new IllegalArgumentException("Unsupported return type: " + returnType.getName());
        }
    }

    @Override
    public List<HotelLocation> findAll() {
        return hotelLocationDAO.readAll()
                .stream()
                .map(hotelLocationMapper::mapToDomain) // Convert each HotelLocationEntity to HotelLocation
                .toList(); // Collect results into a List
    }
}
