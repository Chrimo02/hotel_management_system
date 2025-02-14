package hotelmanagementsystem.infrastructure.persistence.repositories.adapters;

import hotelmanagementsystem.domain.models.HotelLocation;
import hotelmanagementsystem.infrastructure.persistence.dao.interfaces.HotelLocationDAO;
import hotelmanagementsystem.infrastructure.persistence.entities.HotelLocationEntity;
import hotelmanagementsystem.infrastructure.persistence.mapper.HotelLocationMapper;
import hotelmanagementsystem.infrastructure.persistence.repositories.interfaces.HotelLocationRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class HotelLocationDatabaseAdapter implements HotelLocationRepository {
    private final HotelLocationDAO hotelLocationDAO;
    private final HotelLocationMapper hotelLocationMapper;
    @Inject
    public HotelLocationDatabaseAdapter(HotelLocationDAO hotelLocationDAO, HotelLocationMapper hotelLocationMapper) {
        this.hotelLocationDAO = hotelLocationDAO;
        this.hotelLocationMapper = hotelLocationMapper;
    }

    @Override
    public void createHotelLocation(HotelLocation hotelLocation) {
        HotelLocationEntity hotelLocationEntity = hotelLocationMapper.mapToEntity(hotelLocation);
        hotelLocationDAO.create(hotelLocationEntity);
    }



    @Override
    public void deleteHotelLocation(long hotelLocationId) {
        HotelLocationEntity hotelLocationEntity = hotelLocationDAO.read(hotelLocationId);
        hotelLocationDAO.delete(hotelLocationEntity);
    }
    
}
