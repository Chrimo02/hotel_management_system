package hotelmanagementsystem.infrastructure.persistence.repositories.adapters;

import hotelmanagementsystem.domain.models.Hotel;
import hotelmanagementsystem.infrastructure.persistence.dao.interfaces.HotelDAO;
import hotelmanagementsystem.infrastructure.persistence.mapper.HotelMapper;
import hotelmanagementsystem.infrastructure.persistence.repositories.interfaces.HotelRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class HotelDatabaseAdapter implements HotelRepository {

    private final HotelDAO hotelDAO;
    private final HotelMapper hotelMapper;
    @Inject
    public HotelDatabaseAdapter(HotelDAO hotelDAO, HotelMapper hotelMapper) {

        this.hotelDAO = hotelDAO;
        this.hotelMapper = hotelMapper;

    }

    @Override
    public Hotel createHotel(Hotel hotel) {
        try {
            return hotelMapper.mapHotelEntityToDomainHotel(hotelDAO.createHotel(hotelMapper.mapDomainHotelToHotelEntity(hotel)));
        } catch (Exception e) {
            throw new RuntimeException("Error saving Hotel", e);
        }
    }


    @Override
    public Optional<Hotel> findById(Long id) {
        return hotelDAO.findById(id)
                .map(hotelMapper::mapHotelEntityToDomainHotel);
    }

    @Override
    public List<Hotel> findAll() {
        // Retrieve all entities from the DAO and map them to domain objects
        return hotelDAO.findAll().stream()
                .map(hotelMapper::mapHotelEntityToDomainHotel)
                .collect(Collectors.toList());
    }

    @Override
    public void update(Hotel hotel) {
        try {
            // Map the domain object to the entity and pass it to the DAO for updating
            hotelDAO.updateHotel(hotelMapper.mapDomainHotelToHotelEntity(hotel));
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

}
